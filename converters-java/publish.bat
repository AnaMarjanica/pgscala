@echo off

echo Publishing the project ...
call "%~dp0\sbt.bat" --no-jrebel %* builder/run publish
