@echo off
Title Remove target folders from all subdirectories

dir /A:-HD/S/B target > target_directories.log

echo 'Scanned for 'target' folders:'
for /f %%i in (target_directories.log) do (
	echo %%i
)
echo ------------------------------------------------------
for /f %%i in (target_directories.log) do (
	echo 'Cleaning up - %%i'
	rd/S/Q %%i
)
del /Q target_directories.log

