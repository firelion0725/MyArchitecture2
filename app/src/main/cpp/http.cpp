//
// Created by Leo.ZhangTJ on 2020/8/26.
//

#include <jni.h>
#include <string>

#define JNI_REG_CLASS "com/leo/myarchitecture/cpp/CppHttp"

JNIEXPORT jstring JNICALL
testString(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello test http cpp";
    return env->NewStringUTF(hello.c_str());
}

static JNINativeMethod g_methods[] = {
        {"testString", "()Ljava/lang/String;", (void *) testString}
};

// must define this function
JNIEXPORT int JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    jclass javaClass = env->FindClass(JNI_REG_CLASS);
    if (javaClass == NULL) {
        return JNI_ERR;
    }

    int method_count = sizeof(g_methods) / sizeof(g_methods[0]);
    if (env->RegisterNatives(javaClass, g_methods, method_count) < 0) {
        return JNI_ERR;
    }

    return JNI_VERSION_1_6;
}






