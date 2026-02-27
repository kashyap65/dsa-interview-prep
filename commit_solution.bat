@echo off
:: =============================================================
:: commit_solution.bat — Run after every approved solution
::
:: USAGE:
::   commit_solution.bat <pattern> <ClassName> <Difficulty>
::
:: EXAMPLES:
::   commit_solution.bat sliding_window LongestSubstringNoRepeat Medium
::   commit_solution.bat trees LowestCommonAncestor Medium
::   commit_solution.bat dynamic_programming CoinChange Medium
::   commit_solution.bat system_design 01_Rate_Limiter Design
:: =============================================================

setlocal

set PATTERN=%1
set CLASSNAME=%2
set DIFFICULTY=%3

:: Validate arguments
if "%PATTERN%"=="" goto usage
if "%CLASSNAME%"=="" goto usage
if "%DIFFICULTY%"=="" goto usage

:: Get today's date in "Mon DD" format using PowerShell
for /f "delims=" %%d in ('powershell -command "Get-Date -Format \"MMM dd\""') do set TODAY=%%d

:: Build file path and commit message
if "%PATTERN%"=="system_design" (
  set FILE_PATH=docs/system-design/%CLASSNAME%.md
) else (
  set FILE_PATH=src/main/java/com/kashyap/dsa/%PATTERN%/%CLASSNAME%.java
)

set COMMIT_MSG=✅ [%PATTERN%] %CLASSNAME% — %DIFFICULTY% — %TODAY%

echo ==================================================
echo   Committing approved solution
echo   File    : %FILE_PATH%
echo   Message : %COMMIT_MSG%
echo ==================================================
echo.

git add "%FILE_PATH%"
git commit -m "%COMMIT_MSG%"
git push

echo.
echo ✅ Pushed! View at:
echo    https://github.com/kashyap65/dsa-interview-prep/blob/main/%FILE_PATH%
echo.
goto end

:usage
echo.
echo USAGE:
echo   commit_solution.bat ^<pattern^> ^<ClassName^> ^<Difficulty^>
echo.
echo EXAMPLES:
echo   commit_solution.bat sliding_window LongestSubstringNoRepeat Medium
echo   commit_solution.bat trees LowestCommonAncestor Medium
echo   commit_solution.bat dynamic_programming CoinChange Medium
echo   commit_solution.bat system_design 01_Rate_Limiter Design
echo.

:end
pause
