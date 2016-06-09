@echo off

Title Rename target folders from all subdirectories
echo Collecting target directories to rename
dir /A:-HD/S/B target* > target_directories.log
dir /A:-HD/S/B .settings* >> target_directories.log

for /f %%i in (target_directories.log) do (
	title Renaming up - %%i
	echo Renaming up - %%i
	move %%i %%i1
)
title Renaming is completed.

Title Remove target folders from all subdirectories
echo Collecting target directories to remove
dir /A:-HD/S/B target* > target_directories.log
dir /A:-HD/S/B .settings* >> target_directories.log
for /f %%i in (target_directories.log) do (
	title Cleaning up - %%i
	echo Cleaning up - %%i
	rd/S/Q %%i
)
title target dir cleanup is completed.

del /Q target_directories.log