cmake_minimum_required(VERSION 3.15)
project(Test)
set(CMAKE_CXX_STANDARD 14)

link_directories(./)
add_executable(Test main.cpp)
target_link_libraries (Test LINK_PUBLIC ShadoMath)