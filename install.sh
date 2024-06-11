#!/bin/bash

# Crear el directorio de destino
sudo mkdir -p /opt/GameArchive/

# Copiar los archivos
sudo cp -r target/GameArchive-1.0-SNAPSHOT.jar /opt/GameArchive/
sudo cp -r target/classes/ /opt/GameArchive/

cat <<EOF > ~/.local/share/applications/GameArchive.desktop
[Desktop Entry]
Version=1.0
Type=Application
Name=GameArchive
Exec=java -jar /opt/GameArchive/GameArchive-1.0-SNAPSHOT.jar
Icon=java -jar /opt/GameArchive/classes/icon.png
Terminal=false
EOF

#Hacer el lanzador ejecutable
chmod -x ~/.local/share/applications/GameArchive.desktop

echo "GameArchive: Installation completed. You can find GameArchive in your apps menu."
