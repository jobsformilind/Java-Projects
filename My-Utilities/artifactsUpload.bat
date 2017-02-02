
@echo off
FOR /F "tokens=1-5 delims=," %%A IN (sms.txt) DO (
	mvn deploy:deploy-file -DgroupId=%%A -DartifactId=%%B -Dversion=%%C -Dpackaging=%%D -Dfile=%%E -DgeneratePom=true -DrepositoryId=<repo-id> -Durl=<url>
)
