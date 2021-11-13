package me.tylermoser;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

public class Donut {

    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 640;
    private static final int CANVAS_LENGTH = CANVAS_WIDTH * CANVAS_HEIGHT;
    private static final float TWO_PI = 6.28318f;
    private static final int RUNTIME_SECS = 60;
    private static final boolean DISPLAY = true;

    public static void main(String[] args) throws InterruptedException, IOException {
        long iter = 0;
        long start = Instant.now().getEpochSecond();
        char[] asciiPixels = {'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'};
        DonutWindow window = DISPLAY ? new DonutWindow(CANVAS_WIDTH, CANVAS_HEIGHT) : null;

        float A = 0, B = 0;
        float phi, theta;
        char[] frameBuffer = new char[CANVAS_LENGTH];
        int[] pixelBuffer = new int[CANVAS_LENGTH];
        float[] zBuffer = new float[CANVAS_LENGTH];
        while (true) {
            A += 0.07; // Increment torus rotation
            B += 0.03; // Increment torus rotation
            Arrays.fill(frameBuffer, ' ');
            Arrays.fill(pixelBuffer, (byte) 0xFF);
            Arrays.fill(zBuffer, 0);
            for (theta = 0; theta < TWO_PI; theta += 0.003) { // Originally 0.07
                for (phi = 0; phi < TWO_PI; phi += 0.001) { // Originally 0.02
                    float sTheta = (float) Math.sin(theta), cTheta = (float) Math.cos(theta);
                    float sPhi = (float) Math.sin(phi), cPhi = (float) Math.cos(phi);
                    float sA = (float) Math.sin(A), cA = (float) Math.cos(A);
                    float sB = (float) Math.sin(B), cB = (float) Math.cos(B);

                    float h = cTheta + 2;
                    float t = sPhi * h * cA - sTheta * sA;
                    float oneOverZ = 1 / (sPhi * h * sA + sTheta * cA + 5);
                    int x2d = (int) (320 + 400 * oneOverZ * (cPhi * h * cB - t * sB));
                    int y2d = (int) (320 + 400 * oneOverZ * (cPhi * h * sB + t * cB));
                    int bufferIndex = x2d + CANVAS_WIDTH * y2d;
                    float illumination = (sTheta * sA - sPhi * cTheta * cA) * cB - sPhi * cTheta * sA - sTheta * cA - cPhi * cTheta * sB;
                    int illumination12 = (int) (8 * illumination); // multiplier = floor( # of levels / sqrt(2) )
                    byte illumination255 = (byte) (90 * illumination);
                    if (CANVAS_HEIGHT > y2d && y2d > 0 && x2d > 0 && CANVAS_WIDTH > x2d && oneOverZ > zBuffer[bufferIndex]) {
                        zBuffer[bufferIndex] = oneOverZ;
                        frameBuffer[bufferIndex] = asciiPixels[Math.max(illumination12, 0)];
                        pixelBuffer[bufferIndex] = 128 + illumination255;
                    }
                }
            }

            if (DISPLAY) {
                //printAsciiDonut(frameBuffer);
                window.updateDonut(pixelBuffer);
            }

            iter++;
            if (Instant.now().getEpochSecond() - start > RUNTIME_SECS) {
                System.out.printf("%f\n", ((double)iter / RUNTIME_SECS));
                return;
            }
        }
    }

    private static void printAsciiDonut(char[] frameBuffer) throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
        }
        for (int i = 0; i < CANVAS_LENGTH + 1; i++) {
            System.out.print(i % CANVAS_WIDTH > 0 ? frameBuffer[i] : '\n');
        }
        System.out.flush();
    }

}
