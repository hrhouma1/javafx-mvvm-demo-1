@echo off
echo ========================================
echo   Demonstration Pattern MVVM
echo ========================================
echo.
echo Compilation du projet...
call mvn clean compile

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERREUR lors de la compilation!
    pause
    exit /b 1
)

echo.
echo Lancement de l'application...
echo.
call mvn javafx:run

pause

