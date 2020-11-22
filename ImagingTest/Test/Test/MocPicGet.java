package Test;

import mainClass.PictureGet;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;

public class MocPicGet extends PictureGet{
	public MocPicGet(String FilePath, String PictureName, String format){
        super(FilePath, PictureName, format);
    }
    //�ж������ͼƬ�Ƿ����
    public boolean judgeExistence(){
        //��ʱ�ж��Ƿ���ڵĺ�����δʵ�֣���������ͼƬβ׺�ж�
        if(this.getFilePath().equals("C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture")){
            return true;
        }
        else {
            return false;
        }
    }
    public void printExif(final ImageMetadata metadata){
        //���巽��δʵ�֣�����̶���Ϣ
        if(metadata instanceof JpegImageMetadata){
            System.out.println("����ΪͼƬ��EXIF��Ϣ");
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            System.out.println("XResolution: Not found");
            System.out.println("DateTime: Not found");
            System.out.println("DateTimeOriginal: Not found");
            System.out.println("DateTimeDigitized: Not found");
            System.out.println("PhotographicSensitivity: Not found");
            System.out.println("ShutterSpeedValue: Not found");
            System.out.println("ApertureValue: Not found");
        }
        else {
            System.out.println("������EXIF��Ϣ");
        }
    }
}
