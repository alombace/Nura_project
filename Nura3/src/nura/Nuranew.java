package nura;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author пк
 */
public class Nuranew
{

    private static String drvName = "org.postgresql.Driver";
    private static final String user = "postgres";
    private static final String pwd = "19654216p";
    private static final String dbUrl = "jdbc:postgresql://localhost:5433/nuradb";

    public static boolean loadDrv() 
    { //подключение к драйверу
        boolean ret = false;
        try
        {
            Class.forName(drvName);
            ret = true;
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public static Connection getConn() // подключение к БД
    {
        Connection con = null;
        try
        {
            con = DriverManager.getConnection(dbUrl, user, pwd); // ввод всех данных
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);// исключение на вход
        }
        return con;
    }

    public static int loadBlob(File f, int pic_id)//загрузка в БД из файла картинки 
    {
        int ret = 0;
        String sqli = "insert into picture(pic_id,pic) values(?,?)";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            con.setAutoCommit(false); // работа с BLOB в Postgres должна идти без autocommit
            PreparedStatement pst = con.prepareStatement(sqli);
            pst.setInt(1, pic_id);
            pst.setBinaryStream(2, new FileInputStream(f), (int) f.length());
            ret = pst.executeUpdate();
            con.commit();
        } catch (SQLException | FileNotFoundException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }

    //загрузка добавленной информации в БД
    public static int add_info(int add_id, String add_name, String add_text)
    {
        int ret = 0;
        String sqli = "insert into added_recept(add_id,add_name,add_text) values(?,?,?)";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            //con.setAutoCommit(false); // работа с BLOB в Postgres должна идти без autocommit
            PreparedStatement pst = con.prepareStatement(sqli);
            pst.setInt(1, add_id);
            pst.setString(2, add_name);
            pst.setString(3, add_text);
            ret = pst.executeUpdate();
//            con.commit();
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }

    /**
     * Вспомогательный метод для определения ID
     *
     * @return максимальное значение идентификатора в БД
     */
    public static int getMaxID(String sqls)
    {
        int res = 0;
        // 
        Connection con = getConn();
        if (con == null)
        {
            return res;
        }
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqls);
            if (rs.next())
            {
                res = rs.getInt(1);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public static ArrayList<Integer> getIDS(String sqls) //получение айдишников
    {
        ArrayList<Integer> res = new ArrayList<>();

        Connection con = getConn();
        if (con == null)
        {
            return res;
        }
        Statement st;
        try
        {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqls);
            while (rs.next())
            {
                res.add(rs.getInt(1)); //добавляем в массив, полученные индификаторы с БД
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    /**
     * Метод получения изображения из БД
     *
     * @param pic_id идетификатор
     * @return объект BufferedImage
     */
    public static BufferedImage getBlob(int pic_id)
    {
        BufferedImage ret = null;
        String sqls = "select pic from picture where pic_id=?";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            con.setAutoCommit(false); // работа с BLOB в Postgres должна идти без autocommit
            PreparedStatement pst = con.prepareStatement(sqls);
            pst.setInt(1, pic_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                byte[] imgBytes = rs.getBytes(1);
                ret = ImageIO.read(new ByteArrayInputStream(imgBytes));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
    
    public static String getinfo(int pic_id) //получение названия рецепта
    {   
        String ret = null;
        String sqls = "select recept_name from recept where pic_id=?";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            
            PreparedStatement pst = con.prepareStatement(sqls);
            pst.setInt(1, pic_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {

                ret = rs.getString("recept_name");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    public static String gettext(int pic_id)//получение описания рецепта
    {
        String ret = null;
        String sqls = "select recept_des from recept where pic_id=?";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            
            PreparedStatement pst = con.prepareStatement(sqls);
            pst.setInt(1, pic_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {

                ret = rs.getString("recept_des");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    public static String get_added_info(int add_id) //получение названия добавленного рецепта
    {
        String ret = null;
        String sqls = "select add_name from added_recept where add_id=?";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
         
            PreparedStatement pst = con.prepareStatement(sqls);
            pst.setInt(1, add_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {

                ret = rs.getString("add_name");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    public static String get_added_info1(int add_id)//получение описания добавленного рецепта
    {
        String ret = null;
        String sqls = "select add_text from added_recept where add_id=?";
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
           
            PreparedStatement pst = con.prepareStatement(sqls);
            pst.setInt(1, add_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {

                ret = rs.getString("add_text");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

   public static boolean work_favourite(int pic_id) // получение значения поля "избранное"
    {   // по нужному айди
       boolean ret = false;
        String sqls = "select favourite_n from recept where pic_id=?"; 
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            // con.setAutoCommit(false); // работа с BLOB в Postgres должна идти без autocommit
            PreparedStatement pst = con.prepareStatement(sqls);
            pst.setInt(1, pic_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {

                ret = rs.getBoolean("favourite_n");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
    

     public static int fav1(int pic_id,String sqli) // вспомогательный метод загрузки и поиска
    {
       int ret = 0;
      
        Connection con = getConn();
        if (con == null)
        {
            return ret;
        }
        try
        {
            //con.setAutoCommit(false); // работа с BLOB в Postgres должна идти без autocommit
            PreparedStatement pst = con.prepareStatement(sqli);
            pst.setInt(1, pic_id);
            
            ret = pst.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Nuranew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
      

}
