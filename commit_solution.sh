#!/bin/bash
# =============================================================
# commit_solution.sh — Run this after every approved solution
#
# USAGE:
#   ./commit_solution.sh <pattern> <ClassName> <Difficulty>
#
# EXAMPLES:
#   ./commit_solution.sh sliding_window LongestSubstringNoRepeat Medium
#   ./commit_solution.sh trees LowestCommonAncestor Medium
#   ./commit_solution.sh dynamic_programming CoinChange Medium
#   ./commit_solution.sh system_design 01_Rate_Limiter Design
# =============================================================

PATTERN=$1
CLASSNAME=$2
DIFFICULTY=$3
DATE=$(date +"%b %d")

if [ -z "$PATTERN" ] || [ -z "$CLASSNAME" ] || [ -z "$DIFFICULTY" ]; then
  echo "Usage: ./commit_solution.sh <pattern> <ClassName> <Difficulty>"
  echo "Example: ./commit_solution.sh sliding_window LongestSubstringNoRepeat Medium"
  exit 1
fi

# Determine file path based on pattern type
if [ "$PATTERN" = "system_design" ]; then
  FILE_PATH="docs/system-design/$CLASSNAME.md"
else
  FILE_PATH="src/main/java/com/kashyap/dsa/$PATTERN/$CLASSNAME.java"
fi

COMMIT_MSG="✅ [$PATTERN] $CLASSNAME — $DIFFICULTY — $DATE"

echo "=================================================="
echo "  Committing approved solution"
echo "  File: $FILE_PATH"
echo "  Message: $COMMIT_MSG"
echo "=================================================="

git add "$FILE_PATH"
git commit -m "$COMMIT_MSG"
git push

echo ""
echo "✅ Pushed! View at: https://github.com/kashyap65/dsa-interview-prep/blob/main/$FILE_PATH"
