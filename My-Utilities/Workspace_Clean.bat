del /S /Q 1*.index
del /S /Q 2*.index
del /S /Q 3*.index
del /S /Q 4*.index
del /S /Q 5*.index
del /S /Q 6*.index
del /S /Q 7*.index
del /S /Q 8*.index
del /S /Q 9*.index
del /S /Q 0*.index
del /S /Q history.index
del /S /Q refactorings.index
del /S /Q properties.index
del /S /Q history.version
del /S /Q earLibrariesCache.index
del /S /Q webLibrariesCache.index
del /S /Q earLibrariesCache.index
del /S /Q properties.version
del /S /Q upload0.csv
del /S /Q upload1.csv
del /S /Q upload2.csv
del /S /Q upload3.csv
del /S /Q upload4.csv
del /S /Q upload5.csv
del /S /Q *.log
del /S /Q savedIndexNames.txt
del /S /Q participantsIndexNames.txt
del /S /Q .lock


@echo off
Title Remove index folders from all subdirectories

dir /A:-HD/S/B *indexes > index_directories.log

for /f %%i in (index_directories.log) do (
	echo Cleaning up - %%i
	rd/S/Q %%i
)



