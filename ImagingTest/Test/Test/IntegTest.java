package Test;

import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;

/*
* PictureGet��setRGB��PictureIO��δ��ɣ������������̳�PictureGet��setRGB��������δʵ�ֵķ���
* PictureIOд������ͨ��ImageIOʵ�֣���������ͨ��ImageIO.read��ȡ��ͼƬ��ֱ��ʹ��
* */
@RunWith(MockitoJUnitRunner.class)
public class IntegTest {
	private static JpegImageMetadata stubinfo;
    private static BufferedImage stubImage;
    private static BufferedImage stubImageNull;
    private static String stubPath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture";
    private static String stubName = "wangtian";
    private static String stubFormat = ".jpg";
    private static String stubPicPath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���" +
            "\\�������\\�������ʵ��\\testpicture\\wangtian.jpg";
    private static String TrueNewPath = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture\\����.jpg";
    private static String  stubPathNull = "E:\\��Ѷ����";
    private static String stubExifNull = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���" +
            "\\�������\\�������ʵ��\\testpicture\\�Ͼ���.jpg";

    @BeforeClass
    public static void prepare() throws Exception{
        //Ԥ�ȶ�ȡһ��ͼƬ���䵱�޸���ɫ��Ŀ��ͼƬ
        File file = new File(stubPicPath);
        stubImage = ImageIO.read(file);
        stubImageNull = null;
    }
    @Test
    //�˲��Բ��Ե�������ִ��
    public void testIntegTrue() throws Exception{
        MocPicGet stubGet = new MocPicGet(stubPath, stubName, stubFormat);
        boolean existence = stubGet.judgeExistence();
        assertTrue(existence);
        if(existence){
            stubGet.printExif(stubGet.getExif(stubPicPath));
            if(stubImage != null) {
                BufferedImage newImg = MocSetRGB.setRGB(stubImage, 1, 1,
                        1, 1, 1, 1);
                assertNotNull(newImg);
                if(newImg != null) {
                    ImageIO.write(newImg, "jpg", new File(TrueNewPath));
                }
                else {
                    System.out.println("�������ط�Χ����");
                }
            }
            else {
                System.out.println("��ȡͼƬ����ͼƬ��ɾ��");
            }
        }
        else {
            System.out.println("��ȡ�ļ�������");
        }
    }
    @Test
    //�˲��Բ��Ե�����������Ч��ͼƬ·��
    public void testIntegFlase() throws Exception{
        //������Ŀ¼��ַ�����ڲ��Ҳ�����Ӧ��ͼƬ
        MocPicGet stubGet = new MocPicGet(stubPathNull, stubName, stubFormat);
        boolean existence = stubGet.judgeExistence();
        assertFalse(existence);
        if(existence){
            stubGet.printExif(stubGet.getExif(stubPicPath));
            //��ʱ���ص㳬����Χ��newImgӦ�÷���null
            if(stubImage != null){
                BufferedImage newImg = MocSetRGB.setRGB(stubImage, 1, 1,
                        1, 1, 1, 1);
                assertNull(newImg);
                if(newImg != null) {
                    ImageIO.write(newImg, "jpg", new File(TrueNewPath));
                }
                else {
                    System.out.println("�������ط�Χ����");
                }
            }
            else {
                System.out.println("��ȡͼƬ����ͼƬ��ɾ��");
            }
        }
        else {
            //�˲���ִ������
            System.out.println("��ȡ�ļ�������");
        }
    }
    @Test
    //�˲��Բ��Ե��ǽ�ͼ��ָ����ɫ�滻��������ɫʱ������ָ����ɫ�������ص�ʱ�����ص�����Խ�磬��ͼƬ��EXIF��Ϣ
    public void TestIntegFalse2() throws Exception{
        MocPicGet stubGet = new MocPicGet(stubPath, stubName, stubFormat);
        boolean existence = stubGet.judgeExistence();
        assertTrue(existence);
        if(existence){
            stubGet.printExif(stubGet.getExif(stubExifNull));
            if(stubImage != null){
                BufferedImage newImg = MocSetRGB.setRGB(stubImage, 8069, 1,
                        1, 1, 1, 1);
                assertNull(newImg);
                if(newImg != null) {
                    ImageIO.write(newImg, "jpg", new File(TrueNewPath));
                }
                else{
                    System.out.println("�������ط�Χ����");
                }
            }
            else {
                System.out.println("��ȡͼƬ������ͼƬ��ɾ��");
            }
        }
        else {
            System.out.println("��ȡ�ļ�������");
        }
    }
    @Test
    //�˲��Բ��Ե��ǵ�һ�ζ�ȡEXIF��ϢʱͼƬ���ڣ��ڶ������¶�ȡͼƬ���޸����ص�ʱ��ͼƬ��ɾ�������ܷ���ȷִ��
    public void TestIntegFalse3() throws Exception{
        MocPicGet stubGet = new MocPicGet(stubPath, stubName, stubFormat);
        boolean existence = stubGet.judgeExistence();
        assertTrue(existence);
        if(existence){
            stubGet.printExif(stubGet.getExif(stubPicPath));
            if(stubImageNull != null){
                BufferedImage newImg = MocSetRGB.setRGB(stubImage, 8069, 1,
                        1, 1, 1, 1);
                assertNull(newImg);
                if(newImg != null) {
                    ImageIO.write(newImg, "jpg", new File(TrueNewPath));
                }
                else{
                    System.out.println("�������ط�Χ����");
                }
            }
            else {
                System.out.println("��ȡͼƬ������ͼƬ��ɾ��");
            }
        }
        else {
            System.out.println("��ȡ�ļ�������");
        }
    }
}
