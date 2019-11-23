SUMMARY = "Linux Kernel"
DESCRIPTION = "Linux Kernel build for Linux Application"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit kernel

KDEFCONFIG ?= "hhc7510_defconfig"

SRC_URI = "git://gokulbitbucket@bitbucket.org/gokulbitbucket/hhc75_nuc_kernel.git;protocol=http;user=gokulbitbucket:xbnMNhu2khUWbU2cwSAh \
					 file://compilation_fix.patch"
KBRANCH = "master"
SRCREV = "32e2788744d4f99b7cacd6af336da62a8c78cc08"

S = "${WORKDIR}/git"

do_patch[postfuncs] += "copy_defconfig "

copy_defconfig(){
	if [ ! -e ${B}/../image ]; then
		mkdir ${B}/../image
	fi
	if [ ! -z ${KDEFCONFIG} ]; then
        cp ${S}/arch/${ARCH}/configs/${KDEFCONFIG} ${B}/.config
				sed -i 's/arm-poky-linux-gnueabi-/arm-emlinux-linux-gnueabi-/g' ${S}/Makefile
    fi
}

#do_install[postfuncs] += "create_uImage"

#create_uImage(){
#	
#}
