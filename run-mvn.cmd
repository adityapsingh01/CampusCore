@echo off
rem ------------------------------------------------------------
rem Local Maven wrapper that ensures JAVA_HOME points to a valid JDK
rem   Priority:
rem     1. Existing JAVA_HOME if it contains bin\java.exe
rem     2. javac.exe from PATH (to get JDK home)
rem     3. java.exe from PATH (fallback, may be JRE only)
rem     4. Hardcoded fallback (uncomment if needed)
rem ------------------------------------------------------------

rem Function to check if a directory is a valid JDK (has bin\java.exe)
:isValidJdkDir
set "JDK_DIR=%~1"
if exist "%JDK_DIR%\bin\java.exe" (
    exit /b 0
) else (
    exit /b 1
)
goto :eof

rem Start with empty JAVA_HOME
set "JAVA_HOME="

rem 1. Check existing JAVA_HOME
if defined JAVA_HOME (
    call :isValidJdkDir "%JAVA_HOME%"
    if not errorlevel 1 (
        for %%I in ("%JAVA_HOME%") do set "JAVA_HOME=%%~fI"
        echo Using JAVA_HOME from environment: %JAVA_HOME%
        goto :prepare_mvn
    ) else (
        echo WARNING: JAVA_HOME is set but bin\java.exe not found: %JAVA_HOME%
    )
)

rem 2. Try to find javac.exe in PATH (prefer JDK)
for %%J in (javac.exe) do set "JAVAC_EXE=%%~$PATH:J"
if defined JAVAC_EXE (
    for %%J in ("%JAVAC_EXE%") do set "JAVAC_EXE=%%~fJ"
    set "JAVA_HOME=%JAVAC_EXE%\..\.."
    call :isValidJdkDir "%JAVA_HOME%"
    if not errorlevel 1 (
        for %%I in ("%JAVA_HOME%") do set "JAVA_HOME=%%~fI"
        echo Using JAVA_HOME from javac.exe in PATH: %JAVA_HOME%
        goto :prepare_mvn
    ) else (
        echo WARNING: Found javac.exe at %JAVAC_EXE% but derived JAVA_HOME invalid: %JAVA_HOME%
    )
) else (
    echo INFO: javac.exe not found in PATH
)

rem 3. Try to find java.exe in PATH (fallback)
for %%J in (java.exe) do set "JAVA_EXE=%%~$PATH:J"
if defined JAVA_EXE (
    for %%J in ("%JAVA_EXE%") do set "JAVA_EXE=%%~fJ"
    set "JAVA_HOME=%JAVA_EXE%\.."
    call :isValidJdkDir "%JAVA_HOME%"
    if not errorlevel 1 (
        for %%I in ("%JAVA_HOME%") do set "JAVA_HOME=%%~fI"
        echo Using JAVA_HOME from java.exe in PATH: %JAVA_HOME%
        goto :prepare_mvn
    ) else (
        echo WARNING: Found java.exe at %JAVA_EXE% but bin\java.exe not valid: %JAVA_HOME%
        echo          This may be a JRE only; Maven may fail to compile.
        rem We'll still try to use it
        goto :prepare_mvn
    )
) else (
    echo ERROR: Could not locate java.exe in PATH
)

rem 4. Hardcoded fallback (uncomment and set if needed)
rem set "JAVA_HOME=C:\Program Files\Java\jdk-21"
rem if exist "%JAVA_HOME%\bin\java.exe" (
rem     for %%I in ("%JAVA_HOME%") do set "JAVA_HOME=%%~fI"
rem     echo Using fallback JAVA_HOME: %JAVA_HOME%
rem     goto :prepare_mvn
rem ) else (
rem     echo ERROR: Fallback JAVA_HOME does not point to a valid JDK: %JAVA_HOME%
rem     goto :eof
rem )

echo.
echo Unable to determine a valid JAVA_HOME. Please:
echo   1. Ensure a JDK is installed and its bin directory is in your PATH, OR
echo   2. Set JAVA_HOME system variable to your JDK installation, OR
echo   3. Uncomment and set the fallback path in this script.
echo.
goto :eof

:prepare_mvn
rem Normalize JAVA_HOME (remove trailing spaces, etc.)
for %%I in ("%JAVA_HOME%") do set "JAVA_HOME=%%~fI"
echo.
echo Final JAVA_HOME set to: %JAVA_HOME%
echo.

rem Now delegate to the original Maven wrapper
call "%~dp0mvnw.cmd" %*
goto :eof