SUMMARY = "Make userfs on first boot with systemd-repart; grows to full storage space"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://50-userfs.conf file://usr-local.mount"
S = "${WORKDIR}"

inherit systemd
SYSTEMD_SERVICE:${PN} = "usr-local.mount"

do_install() {
    install -D -m 0644 ${WORKDIR}/50-userfs.conf ${D}${nonarch_libdir}/repart.d/50-userfs.conf
    install -D -m 0644 ${WORKDIR}/usr-local.mount ${D}${systemd_system_unitdir}/usr-local.mount
}

FILES:${PN} += "${nonarch_libdir}/repart.d"
RDEPENDS:${PN} = "e2fsprogs-mke2fs"
