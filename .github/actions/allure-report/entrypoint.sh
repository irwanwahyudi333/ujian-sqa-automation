#!/usr/bin/env bash
set -e

unset JAVA_HOME

INPUT_ALLURE_RESULTS="${INPUT_ALLURE_RESULTS:-target/allure-results}"
INPUT_GH_PAGES="${INPUT_GH_PAGES:-gh-pages}"
INPUT_ALLURE_REPORT="${INPUT_ALLURE_REPORT:-allure-report}"
INPUT_ALLURE_HISTORY="${INPUT_ALLURE_HISTORY:-allure-history}"
INPUT_KEEP_REPORTS="${INPUT_KEEP_REPORTS:-25}"

mkdir -p "./${INPUT_GH_PAGES}"
mkdir -p "./${INPUT_ALLURE_HISTORY}"
if [ -d "./${INPUT_GH_PAGES}" ]; then
    cp -r "./${INPUT_GH_PAGES}/." "./${INPUT_ALLURE_HISTORY}/" 2>/dev/null || true
fi

REPOSITORY_OWNER_SLASH_NAME="${INPUT_GITHUB_REPO:-$GITHUB_REPOSITORY}"
REPOSITORY_NAME="${REPOSITORY_OWNER_SLASH_NAME##*/}"
REPO_OWNER="${INPUT_GITHUB_REPO_OWNER:-$GITHUB_REPOSITORY_OWNER}"

if [[ -n "${REPO_OWNER}" && -n "${REPOSITORY_NAME}" ]]; then
    GITHUB_PAGES_WEBSITE_URL="https://${REPO_OWNER}.github.io/${REPOSITORY_NAME}"
else
    GITHUB_PAGES_WEBSITE_URL="https://localhost"
fi

if [[ -n "${INPUT_SUBFOLDER}" ]]; then
    INPUT_ALLURE_HISTORY="${INPUT_ALLURE_HISTORY}/${INPUT_SUBFOLDER}"
    INPUT_GH_PAGES="${INPUT_GH_PAGES}/${INPUT_SUBFOLDER}"
    echo "NEW allure history folder ${INPUT_ALLURE_HISTORY}"
    mkdir -p "./${INPUT_ALLURE_HISTORY}"
    GITHUB_PAGES_WEBSITE_URL="${GITHUB_PAGES_WEBSITE_URL}/${INPUT_SUBFOLDER}"
    echo "NEW github pages url ${GITHUB_PAGES_WEBSITE_URL}"
fi

if [[ -n "${INPUT_REPORT_URL}" ]]; then
    GITHUB_PAGES_WEBSITE_URL="${INPUT_REPORT_URL}"
    echo "Replacing github pages url with user input: ${GITHUB_PAGES_WEBSITE_URL}"
fi

COUNT=$(ls -1 "./${INPUT_ALLURE_HISTORY}" 2>/dev/null | wc -l)
echo "Current folders in allure-history: ${COUNT}"
echo "Keep reports count: ${INPUT_KEEP_REPORTS}"
INPUT_KEEP_REPORTS=$((INPUT_KEEP_REPORTS + 1))
if (( COUNT > INPUT_KEEP_REPORTS )); then
    cd "./${INPUT_ALLURE_HISTORY}"
    rm -rf index.html last-history
    echo "Pruning old reports..."
    ls -1 | sort -n | grep -v 'CNAME' | head -n -$((${INPUT_KEEP_REPORTS}-2)) | xargs rm -rf 2>/dev/null || true
    cd "${GITHUB_WORKSPACE:-/app}"
fi

RUN_NUM="${INPUT_GITHUB_RUN_NUM:-$GITHUB_RUN_NUMBER}"
RUN_NUM="${RUN_NUM:-1}"
RUN_ID="${INPUT_GITHUB_RUN_ID:-$GITHUB_RUN_ID}"
RUN_ID="${RUN_ID:-1}"
SERVER_URL="${INPUT_GITHUB_SERVER_URL:-${GITHUB_SERVER_URL:-https://github.com}}"

echo "<!DOCTYPE html><meta charset=\"utf-8\"><meta http-equiv=\"refresh\" content=\"0; URL=${GITHUB_PAGES_WEBSITE_URL}/${RUN_NUM}/index.html\"><meta http-equiv=\"Pragma\" content=\"no-cache\"><meta http-equiv=\"Expires\" content=\"0\">" > "./${INPUT_ALLURE_HISTORY}/index.html"

# executor.json
mkdir -p "./${INPUT_ALLURE_RESULTS}"
[ -z "$INPUT_REPORT_NAME" ] && INPUT_REPORT_NAME="Allure Report with history"
cat <<EOF > "./${INPUT_ALLURE_RESULTS}/executor.json"
{
  "name": "GitHub Actions",
  "type": "github",
  "reportName": "${INPUT_REPORT_NAME}",
  "url": "${GITHUB_PAGES_WEBSITE_URL}",
  "reportUrl": "${GITHUB_PAGES_WEBSITE_URL}/${RUN_NUM}",
  "buildUrl": "${SERVER_URL}/${REPOSITORY_OWNER_SLASH_NAME}/actions/runs/${RUN_ID}",
  "buildName": "GitHub Actions Run #${RUN_ID}",
  "buildOrder": "${RUN_NUM}"
}
EOF

# Copy previous history to results
if [ -d "./${INPUT_GH_PAGES}/last-history" ]; then
    echo "Copying previous allure history from ./${INPUT_GH_PAGES}/last-history to ./${INPUT_ALLURE_RESULTS}/history"
    mkdir -p "./${INPUT_ALLURE_RESULTS}/history"
    cp -r "./${INPUT_GH_PAGES}/last-history/." "./${INPUT_ALLURE_RESULTS}/history/" 2>/dev/null || true
fi

echo "Generating Allure report from ./${INPUT_ALLURE_RESULTS} to ./${INPUT_ALLURE_REPORT} ..."
allure generate --clean "./${INPUT_ALLURE_RESULTS}" -o "./${INPUT_ALLURE_REPORT}"

echo "Copying allure-report to ./${INPUT_ALLURE_HISTORY}/${RUN_NUM}"
mkdir -p "./${INPUT_ALLURE_HISTORY}/${RUN_NUM}"
cp -r "./${INPUT_ALLURE_REPORT}/." "./${INPUT_ALLURE_HISTORY}/${RUN_NUM}/"

echo "Copying allure-report history to ./${INPUT_ALLURE_HISTORY}/last-history"
mkdir -p "./${INPUT_ALLURE_HISTORY}/last-history"
if [ -d "./${INPUT_ALLURE_REPORT}/history" ]; then
    cp -r "./${INPUT_ALLURE_REPORT}/history/." "./${INPUT_ALLURE_HISTORY}/last-history/" 2>/dev/null || true
fi

echo "Allure report generation completed successfully."
