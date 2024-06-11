; Define your installer name and output file
OutFile "GameArchiveInstaller.exe"

; Set the installer name
Name "Game Archive Installer"

; Define the default installation directory
InstallDir "$PROGRAMFILES\GameArchive"

; Request application privileges for Windows Vista and higher
RequestExecutionLevel admin

; Include the Modern UI 2
!include "MUI2.nsh"

; MUI settings
!define MUI_ABORTWARNING

; Installer pages
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

; Uninstaller pages
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_UNPAGE_FINISH

; Language files
!insertmacro MUI_LANGUAGE "English"

; Define installation section
Section "Install"

  ; Set output path to the installation directory
  SetOutPath "$INSTDIR"

  ; Create directories for classes and libraries
  CreateDirectory "$INSTDIR\resources"
  CreateDirectory "$INSTDIR\lib"

  ; Copy the jar file
  File "target\GameArchive-1.0-SNAPSHOT.jar"

  ; Copy the classes
  SetOutPath "$INSTDIR\resources"
  File /r "target\resources\*"

  ; Copy the libraries
  SetOutPath "$INSTDIR\lib"
  File /r "target\lib\*"

  ; Create a shortcut for the application
  CreateShortcut "$DESKTOP\GameArchive.lnk" "$INSTDIR\GameArchive-1.0-SNAPSHOT.jar"

  ; Write the uninstaller
  WriteUninstaller "$INSTDIR\uninstall.exe"

SectionEnd

; Define uninstallation section
Section "Uninstall"

  ; Remove files and directories
  Delete "$INSTDIR\GameArchive-1.0-SNAPSHOT.jar"
  RMDir /r "$INSTDIR\resources"
  RMDir /r "$INSTDIR\lib"

  ; Remove shortcut
  Delete "$DESKTOP\GameArchive.lnk"

  ; Remove the installation directory
  RMDir /r "$INSTDIR"

SectionEnd
