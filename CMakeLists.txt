cmake_minimum_required(VERSION 3.20)
project(Donut)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(.idea)

add_executable(Donut
        .idea/.gitignore
        .idea/Donut.iml
        .idea/modules.xml
        .idea/vcs.xml
        .idea/workspace.xml
        Domut.c
        Domut.exe
        Donut.cpp
        Donut.h
        Donut.py
        README.md)
