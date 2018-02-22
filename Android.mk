LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)

LOCAL_MODULE := HWMessengerLib

LOCAL_SRC_FILES := $(call all-java-files-under, lib_src/java)
LOCAL_SRC_FILES += $(call all-Iaidl-files-under, lib_src/aidl)
LOCAL_AIDL_INCLUDES := vendor/VR/HWMessenger/lib_src/aidl
LOCAL_JACK_ENABLED := disabled 

include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(call all-java-files-under, apk_src)
LOCAL_MODULE := TestHWMessenger
LOCAL_STATIC_JAVA_LIBRARIES = HWMessengerLib
include $(BUILD_JAVA_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE :=thwml
LOCAL_SRC_FILES := thwml
LOCAL_MODULE_CLASS := EXECUTABLES
LOCAL_MODULE_TAGS := optional
include $(BUILD_PREBUILT)


