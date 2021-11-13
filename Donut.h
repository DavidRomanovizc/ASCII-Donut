//
// Created by Romanov on 13.11.2021.
//

#ifndef DONUT_DONUT_H
#define DONUT_DONUT_H
#include <cmath>
#include <iostream>
#include <windows.h>

#include <cstdio>
#include <cmath>


void gotoxy(SHORT x, SHORT y) {
	static HANDLE h = nullptr;
	if (!h)
		h = GetStdHandle(STD_OUTPUT_HANDLE);
	COORD c = { x, y };
	SetConsoleCursorPosition(h, c);
}


#endif //DONUT_DONUT_H
