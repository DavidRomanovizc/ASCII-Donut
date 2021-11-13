//
// Created by Romanov on 13.11.2021.
//

#include "Donut.h"

int main() {
	float A = 0, B = 0;
	float i, j;
	int k;
	float z[1760];
	char b[1760];
	std::cout << "\x1b[2J";
	while (true) {
		memset(b, 32, 1760);
		memset(z, 0, 7040);
		for (j = 0; j < 6.28; j += 0.07) {
			for (i = 0; i < 6.28; i += 0.02) {
				float c = std::sin(i);
				float d = std::cos(j);
				float e = std::sin(A);
				float f = std::sin(j);
				float g = std::cos(A);
				float h = d + 2;
				float D = 1 / (c * h * e + f * g + 5);
				float l = std::cos(i);
				float m = std::cos(B);
				float n = std::sin(B);
				float t = c * h * g - f * e;
				int x = 40 + 30 * D * (l * h * m - t * n);
				int y = 12 + 15 * D * (l * h * n + t * m);
				int o = x + 80 * y;
				int N = 8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n);
				if (22 > y && y > 0 && x > 0 && 80 > x && D > z[o]) {
					z[o] = D;
					b[o] = ".,-~:;=!*#$@"[N > 0 ? N : 0];
				}
			}
		}
		std::cout << "\x1b[H";
		for (k = 0; k < 1761; k++) {
			putchar(k % 80 ? b[k] : 10);
			A += 0.00004;
			B += 0.00002;
		}
		gotoxy(0, 0);
	}
	return 0;
}
