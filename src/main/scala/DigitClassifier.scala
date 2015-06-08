package com.acrussell.connectdots

import java.io.{FileInputStream, File}
import javax.imageio.ImageIO

import net.sourceforge.tess4j.util.ImageIOHelper
import net.sourceforge.tess4j.TessAPI1
import net.sourceforge.tess4j.ITessAPI.{TessPageSegMode, TessBaseAPI}

object DigitClassifier {
  private def loadImage(api: TessBaseAPI, filename: String): Unit = {
    val file = new File(filename)
    val image = ImageIO.read(new FileInputStream(file))
    val bitsPerPixel = image.getColorModel.getPixelSize
    val bytesPerPixel = bitsPerPixel / 8
    val bytesPerLine = math.ceil(image.getWidth * bitsPerPixel / 8.0).toInt
    val imageBuffer = ImageIOHelper.getImageByteBuffer(image)
    TessAPI1.TessBaseAPISetImage(
      api,
      imageBuffer,
      image.getWidth,
      image.getHeight,
      bytesPerPixel,
      bytesPerLine)
  }

  def recognize(filename: String): String = {
    val tessBaseApi = TessAPI1.TessBaseAPICreate
    TessAPI1.TessBaseAPISetPageSegMode(
      tessBaseApi,
      TessPageSegMode.PSM_SINGLE_BLOCK)
    TessAPI1.TessBaseAPIInit3(tessBaseApi, "/usr/share/tessdata", "eng")

    loadImage(tessBaseApi, filename)
    TessAPI1.TessBaseAPIGetUTF8Text(tessBaseApi).getString(0, "utf8")
  }
}
