/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.util;

import jakarta.servlet.http.Part;

/**
 *
 * @author Admin
 */
public class ImageUtils {

    public static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;
        contentDisposition = contentDisposition.substring(beginIndex, endIndex);
        return contentDisposition;
    }

}
