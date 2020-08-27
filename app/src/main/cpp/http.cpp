//
// Created by Leo.ZhangTJ on 2020/8/26.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_leo_myarchitecture_cpp_CppHttp_testString(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello http cpp";
    return env->NewStringUTF(hello.c_str());
}






