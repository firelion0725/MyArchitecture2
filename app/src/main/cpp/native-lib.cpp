#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_leo_myarchitecture_cpp_TestNdk_getStringFromC(JNIEnv *env, jclass clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_leo_myarchitecture_cpp_TestNdk_getSum(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a + b;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_leo_myarchitecture_cpp_TestNdk_showToast(JNIEnv *env, jclass clazz, jobject context,
                                                  jstring string) {

    jclass jclz = env->FindClass("android/widget/Toast");
    jmethodID makeText = env->GetStaticMethodID(jclz, "makeText",
                                                "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;");

    jobject toast = env->CallStaticObjectMethod(jclz, makeText, context, string, 0);
    jmethodID set = env->GetMethodID(jclz, "setText", "(Ljava/lang/CharSequence;)V");
    env->CallVoidMethod(toast, set, string);
    jmethodID show = env->GetMethodID(jclz, "show", "()V");
    env->CallVoidMethod(toast, show);
}
