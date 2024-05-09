package com.league.app;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TextExtractor {
    public TextExtractor() {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("C:\\Users\\adada\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
            // the path of your .jpg, .png or .gif file
            String text = tesseract.doOCR(new File("C:\\Users\\adada\\Documents\\ShareX\\Screenshots\\2024-01\\FC24_byUzkGEoE2.png"));
            System.out.println(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
    }

}
