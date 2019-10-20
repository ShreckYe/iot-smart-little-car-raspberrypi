@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  IotSmartCarServer startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and IOT_SMART_CAR_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\IotSmartCarServer-1.0-SNAPSHOT.jar;%APP_HOME%\lib\asynchttpserver-1.0-alpha-20180315.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.3.11.jar;%APP_HOME%\lib\grpc-all-1.17.1.jar;%APP_HOME%\lib\grpc-protobuf-1.17.1.jar;%APP_HOME%\lib\protobuf-java-3.6.1.jar;%APP_HOME%\lib\webcam-capture-0.3.12.jar;%APP_HOME%\lib\pi4j-core-1.2-SNAPSHOT.jar;%APP_HOME%\lib\tensorflow-1.12.0.jar;%APP_HOME%\lib\netty-all-4.1.32.Final.jar;%APP_HOME%\lib\mime-util-2.1.3.jar;%APP_HOME%\lib\JRPiCam-v1.1.1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.3.11.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.11.jar;%APP_HOME%\lib\bridj-0.7.0.jar;%APP_HOME%\lib\slf4j-api-1.7.2.jar;%APP_HOME%\lib\libtensorflow-1.12.0.jar;%APP_HOME%\lib\libtensorflow_jni-1.12.0.jar;%APP_HOME%\lib\grpc-auth-1.17.1.jar;%APP_HOME%\lib\grpc-netty-1.17.1.jar;%APP_HOME%\lib\grpc-okhttp-1.17.1.jar;%APP_HOME%\lib\grpc-protobuf-nano-1.17.1.jar;%APP_HOME%\lib\grpc-testing-1.17.1.jar;%APP_HOME%\lib\grpc-stub-1.17.1.jar;%APP_HOME%\lib\grpc-protobuf-lite-1.17.1.jar;%APP_HOME%\lib\grpc-core-1.17.1.jar;%APP_HOME%\lib\grpc-context-1.17.1.jar;%APP_HOME%\lib\log4j-1.2.14.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.11.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\google-auth-library-credentials-0.9.0.jar;%APP_HOME%\lib\gson-2.7.jar;%APP_HOME%\lib\error_prone_annotations-2.2.0.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.17.jar;%APP_HOME%\lib\guava-26.0-android.jar;%APP_HOME%\lib\opencensus-contrib-grpc-metrics-0.17.0.jar;%APP_HOME%\lib\opencensus-api-0.17.0.jar;%APP_HOME%\lib\netty-codec-http2-4.1.30.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.30.Final.jar;%APP_HOME%\lib\okhttp-2.5.0.jar;%APP_HOME%\lib\okio-1.13.0.jar;%APP_HOME%\lib\proto-google-common-protos-1.0.0.jar;%APP_HOME%\lib\protobuf-javanano-3.0.0-alpha-5.jar;%APP_HOME%\lib\junit-4.12.jar;%APP_HOME%\lib\mockito-core-1.9.5.jar;%APP_HOME%\lib\checker-compat-qual-2.5.2.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\netty-codec-http-4.1.30.Final.jar;%APP_HOME%\lib\netty-handler-4.1.30.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.30.Final.jar;%APP_HOME%\lib\netty-codec-4.1.30.Final.jar;%APP_HOME%\lib\netty-transport-4.1.30.Final.jar;%APP_HOME%\lib\hamcrest-core-1.3.jar;%APP_HOME%\lib\objenesis-1.0.jar;%APP_HOME%\lib\netty-buffer-4.1.30.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.30.Final.jar;%APP_HOME%\lib\netty-common-4.1.30.Final.jar

@rem Execute IotSmartCarServer
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %IOT_SMART_CAR_SERVER_OPTS%  -classpath "%CLASSPATH%" shreckye.iotsmartcar.server.MainKt %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable IOT_SMART_CAR_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%IOT_SMART_CAR_SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
