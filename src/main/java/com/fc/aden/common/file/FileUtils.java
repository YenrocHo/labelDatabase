package com.fc.aden.common.file;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件处理工具类
 * @author fuce 
 * @date: 2018年9月22日 下午10:33:31
 */
public class FileUtils
{

    /**
     * 输出指定文件的byte数组
     * 
     * @param filename 文件
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 读取图片地址 输出到页面
     * @param request
     * @param response
     * @param fileurl
     * @throws IOException 
     */
    public static void readIMGTohtml(HttpServletRequest request, HttpServletResponse response,String fileurl) throws IOException{
    	//读取本地图片输入流
    			FileInputStream inputStream = new FileInputStream(fileurl);
    			int i = inputStream.available();
    			//byte数组用于存放图片字节数据
    			byte[] buff = new byte[i];
    			inputStream.read(buff);
    			//记得关闭输入流
    			inputStream.close();
    			//设置发送到客户端的响应内容类型
    			response.setContentType("image/*");
    			OutputStream out = response.getOutputStream();
    			out.write(buff);
    			//关闭响应输出流
    			out.close();
    }

    /**
     * 读取图片地址 以缩略图形式输出到页面
     * @param request
     * @param response
     * @param fileurl
     * @throws IOException
     */
    public static void readIMGSmallTohtml(HttpServletRequest request, HttpServletResponse response,String fileurl) throws IOException{

        int widthdist = 400;          //自定义压缩
        int heightdist = 250;
        Float rate = 0.3f;          //按原图片比例压缩（默认）
        try {
            File srcfile = new File(fileurl);
            // 检查图片文件是否存在
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
            }
            // 如果比例不为空则说明是按比例压缩
            if (rate != null && rate > 0) {
                //获得源图片的宽高存入数组中
                int[] results = getImgWidthHeight(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    // TODO 按比例缩放或扩大图片大小，将浮点型转为整型,应该缩略成大小100px 左右的图片，目前直接用0.3
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            java.awt.Image src = ImageIO.read(srcfile);
            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            response.setContentType("image/*");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            ImageIO.write(tag,"jpg",outByteStream);
            out.write(outByteStream.toByteArray());
            //关闭文件输出流
            out.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }

    /**
     * 获取图片宽度和高度
     * @param
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            result[0] =src.getWidth(null); // 得到源图片宽
            result[1] =src.getHeight(null);// 得到源图片高
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }
}
