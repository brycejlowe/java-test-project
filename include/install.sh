#!/bin/sh
set -e

INSTALL_PACKAGE="${1:-}"
[ ! -f "${INSTALL_PACKAGE}" ] && echo "Invalid Package" && exit 1

INSTALL_DIR="${2:-}"
[ -z "${INSTALL_DIR}" ] && echo "Install Directory is Required" && exit 1

PORT_NUMBER="${3:-8080}"

INSTALL_PACKAGE=$(realpath "$INSTALL_PACKAGE")
INSTALL_DIR=$(realpath "$INSTALL_DIR")

echo "Installing $(basename "${INSTALL_PACKAGE}") to ${INSTALL_DIR} on Port ${PORT_NUMBER}"

echo "Creating Installation Directory"
mkdir -pv "${INSTALL_DIR}"

echo "Extracting Installation Package"
cd "${INSTALL_DIR}" || exit 1

tar xvf "${INSTALL_PACKAGE}"

echo "Modifying Configuration"
sed -i "s/{{ port }}/${PORT_NUMBER}/g" "${INSTALL_DIR}/config.yml"

echo "Symlinking Supervisor"
cd /etc/service || exit 1
ln -snvf "${INSTALL_DIR}/sv" "$(basename "${INSTALL_DIR}")"