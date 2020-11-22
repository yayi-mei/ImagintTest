package Test;

import mainClass.PictureGet;
import mainClass.SetRGB;
import mainClass.PictureIO;
import org.junit.Test;

import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import static org.junit.Assert.*;


public class JunitImag {
	 @Test
	    public void testGet() throws Exception{
	        //��һ��������������ȷ��ͼƬ·����ͼƬ�����ʽ��ͼƬ��EXIF��Ϣ������ÿ��EXIF��Ϣ��û������
	        String testPath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture";
	        String testName = "��ӥ";
	        String testFormat = ".jpeg";
	        String imgFileName = testPath + "\\" + testName + testFormat;
	        PictureGet test_exp = new PictureGet(testPath, testName, testFormat);
	        //�ж�ͼƬ�Ƿ���ں����Ƿ���ȷִ��
	        assertTrue(test_exp.judgeExistence());
	        //�ж�EXIF��Ϣ�Ƿ���ڣ�����ֻ�ж�4��
	        ImageMetadata metadata = test_exp.getExif(imgFileName);
	        JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
	        TiffField field = jpegMetadata.findEXIFValueWithExactMatch(
	                TiffTagConstants.TIFF_TAG_XRESOLUTION);
	        assertNull(field);
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                TiffTagConstants.TIFF_TAG_DATE_TIME);
	        assertNull(field);
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                ExifTagConstants.EXIF_TAG_ISO);
	        assertNull(field);
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);
	        assertNull(field);

	        //�ڶ���������������ȷ��ͼƬ·����ͼƬ�����ʽ��ͼƬ��EXIF��Ϣ
	        testName = "�Ͼ���";
	        testFormat = ".jpg";
	        imgFileName = testPath + "\\" + testName + testFormat;
	        //������ȡEXIFͼƬ��Ϣ��
	        test_exp = new PictureGet(testPath, testName, testFormat);
	        assertTrue(test_exp.judgeExistence());
	        //��ȡEXIF��Ϣ��
	        metadata = test_exp.getExif(imgFileName);
	        jpegMetadata = (JpegImageMetadata) metadata;
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                TiffTagConstants.TIFF_TAG_XRESOLUTION);
	        assertEquals("350", field.getValueDescription());
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                TiffTagConstants.TIFF_TAG_DATE_TIME);
	        assertEquals("'2019:10:06 20:21:57'", field.getValueDescription());
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                ExifTagConstants.EXIF_TAG_ISO);
	        assertEquals("800", field.getValueDescription());
	        field = jpegMetadata.findEXIFValueWithExactMatch(
	                ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);
	        assertEquals("-6816/2560 (-2.663)", field.getValueDescription());

	        //������������������ȷ��ͼƬ·����ͼƬ�����ʽ��ͼƬ����EXIF��Ϣ
	        testName = "�Ļ�";
	        testFormat = ".jpg";
	        imgFileName = testPath + "\\" + testName + testFormat;
	        //������ȡEXIFͼƬ��Ϣ��
	        test_exp = new PictureGet(testPath, testName, testFormat);
	        assertTrue( test_exp.judgeExistence());
	        //��ȡEXIF��Ϣ��
	        metadata = test_exp.getExif(imgFileName);
	        assertFalse(metadata instanceof JpegImageMetadata);

	        //���ĸ�������������ͼƬ������ȷ
	        testPath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��";
	        testName = "û��";
	        testFormat = ".jpg";
	        imgFileName = testPath + "\\" + testName + testFormat;
	        //������ȡEXIFͼƬ��Ϣ��PictureGet
	        test_exp = new PictureGet(testPath, testName, testFormat);
	        assertFalse( test_exp.judgeExistence());

	        //���ĸ���������������ʽ����ȷ
	        testName = "�Ͼ���";
	        testFormat = ".png";
	        imgFileName = testPath + "\\" + testName + testFormat;
	        //������ȡEXIFͼƬ��Ϣ��PictureGet
	        test_exp = new PictureGet(testPath, testName, testFormat);
	        assertFalse( test_exp.judgeExistence());

	        //�����������������·������ȷ
	        testName = "�Ͼ���";
	        testFormat = ".jpg";
	        imgFileName = testPath + "\\" + testName + testFormat;
	        //������ȡEXIFͼƬ��Ϣ��PictureGet
	        test_exp = new PictureGet(testPath, testName, testFormat);
	        assertEquals(false , test_exp.judgeExistence());
	    }
	    //���Զ�ȡ��д��ͼƬ
	    @Test
	    public void testIO() throws Exception{
	        //���Զ�ȡͼƬIO
	        //��һ��������������ȫ��ȷ��ͼƬ·�����ж϶�ȡ��ȷ�ķ�ʽ����ImageIO.read��ȡ��ͳһͼƬ�ж��Ƿ���ͬ
	        String imagePath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture\\�Ļ�.jpg";
	        File file = new File(imagePath);
	        BufferedImage correct_image = ImageIO.read(file);
	        BufferedImage image = PictureIO.getGoalImage(imagePath);
	        for (int i = 0; i < correct_image.getWidth(); i++) {
	            for (int t = 0; t < correct_image.getHeight(); t++){
	                assertEquals(correct_image.getRGB(i, t), image.getRGB(i, t));
	            }
	        }
	        //�ڶ������������������ڵ�ͼƬ·��������null����δ��ȡ��
	        imagePath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\������.jpg";
	        image = PictureIO.getGoalImage(imagePath);
	        assertNull(image);
	        //����������������дͼƬ����True����дͼƬ�ɹ�
	        imagePath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture\\�Ļ�.jpg";
	        String Path = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture";
	        String Name = "test";
	        file = new File(imagePath);
	        correct_image = ImageIO.read(file);
	        assertTrue(PictureIO.writeGoalImage(correct_image, Path, Name));
	    }
	    //����������ɫ����
	    @Test
	    public void testSet() throws Exception{
	        //��һ��������������ȷ
	        int xiangsu_x = 16;
	        int xiangsu_y = 2014;
	        int new_red = 255;
	        int new_green = 255;
	        int new_blue = 0;
	        int new_ar = 255;
	        int goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        int orgArgb = 0;
	        String imagePath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture\\��ҹ.jpeg";
	        BufferedImage orgImage = PictureIO.getGoalImage(imagePath);
	        orgArgb = orgImage.getRGB(xiangsu_x, xiangsu_y);
	        BufferedImage newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        //������ص�ɨ�裬�鿴�Ƿ����Ҫ��
	        for (int i = 0; i < orgImage.getWidth(); i++) {
	            for (int t = 0; t < orgImage.getHeight(); t++){
	                if(orgImage.getRGB(i,t) == orgArgb)
	                    assertEquals(newImage.getRGB(i, t), goalArgb);
	                else assertEquals(orgImage.getRGB(i,t), newImage.getRGB(i,t));
	            }
	        }

	        //�ڶ��������������ɼ������ص����Խ��
	        xiangsu_x = 5068;
	        xiangsu_y = 2014;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        orgArgb = 0;
	        //��Ϊ�������ص�Խ�磬��ʱ���ص�Ӧ���ǿ�ָ��
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //�����������������ɼ������ص�����Խ��
	        xiangsu_x = 25;
	        xiangsu_y = 9066;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //���ĸ�����������rgb��ɫֵ��redֵԽ�Ͻ�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 308;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //���������������rgb��greenֵԽ�Ͻ�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 255;
	        new_green = 256;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //����������������RGB��blueֵԽ�Ͻ�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 286;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //���߸�����������rgb��blueֵԽ�½�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 255;
	        new_green = 255;
	        new_blue = -521;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //�ڰ˸�����������rgb��greenֵԽ�½�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 255;
	        new_green = -1;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //�ھŸ�����������rgb��ɫֵ��redֵԽ�½�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = -308;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //��ʮ��������������͸����Խ�Ͻ�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 256;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //��ʮһ��������������͸����Խ�½�
	        xiangsu_x = 25;
	        xiangsu_y = 2018;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = -256;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //��ʮ���������������ɼ������ص����Խ�½�
	        xiangsu_x = -6;
	        xiangsu_y = 2014;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        orgArgb = 0;
	        //��Ϊ�������ص�Խ�磬��ʱ���ص�Ӧ���ǿ�ָ��
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);

	        //��ʮ���������������ɼ������ص�����Խ�½�
	        xiangsu_x = 36;
	        xiangsu_y = -2014;
	        new_red = 255;
	        new_green = 255;
	        new_blue = 0;
	        new_ar = 255;
	        goalArgb = (new_ar << 24) | (new_red << 16) | (new_green << 8) | new_blue;
	        orgArgb = 0;
	        //��Ϊ�������ص�Խ�磬��ʱ���ص�Ӧ���ǿ�ָ��
	        newImage = SetRGB.setRGB(orgImage, xiangsu_x,
	                xiangsu_y, new_red, new_green, new_blue, new_ar);
	        assertNull(newImage);
	    }
}
