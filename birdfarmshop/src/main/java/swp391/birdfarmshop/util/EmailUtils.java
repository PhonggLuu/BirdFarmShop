/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.util;

import jakarta.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.OrderedAccessoryItem;
import swp391.birdfarmshop.model.OrderedBirdItem;
import swp391.birdfarmshop.model.OrderedBirdPairItem;
import swp391.birdfarmshop.util.Constants;

/**
 *
 * @author Admin
 */
public class EmailUtils {

    public static String sendActive(String name, String token) {
        String message = "<html>\n"
                + "    <body>\n"
                + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border:0;max-width:600px;background:#f8f9fa; margin: auto; margin-top: 40px\"width=\"100%\">\n"
                + "            <tbody>\n"
                + "                <tr>\n"
                + "                    <td style=\"text-align:center;border-bottom-color:orange;border-bottom-style:solid;border-bottom-width:10px;border-top-width:0; padding-top: 15px; padding-bottom: 10px;\">\n"
                + "                          <img src=\"" + Constants.C3_HOST + "logo.png\" alt=\"V.E.T\">    \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px\">\n"
                + "                    <td style=\"padding-top: 30px;padding-left: 20px; font-weight: bold;\">\n"
                + "                        Xin chào " + name + ",  \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px\">\n"
                + "                    <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                        Chào mừng bạn đã đến với V.E.T - Thế giới vẹt cảnh. Để sử dụng tài khoản vui lòng bấm nút bên dưới để kích hoạt tài khoản:\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px; \">\n"
                + "                    <td style=\"padding-top: 50px; padding-right: 40px ;padding-left: 20px; text-align: center; padding-bottom: 20px\">\n"
                + "                        <a href=\"" + Constants.HOST + "MainController?action=active&token=" + token + "\"><button style=\"background-color: orange; border:none; line-height: 40px; color: white; font-weight: bold; padding: 0 10px; cursor: pointer;\">Bấm nút</button></a>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px;\">\n"
                + "                    <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px; padding-bottom: 40px\">\n"
                + "                        Chúng tôi xin cảm ơn và chúc bạn có nhiều trải nghiệm tốt,\n"
                + "                        <br>\n"
                + "                        V.E.T\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "            </tbody>\n"
                + "        </table>\n"
                + "    </body>\n"
                + "</html>\n";
        return message;
    }

    public static String sendPassword(String name, String password) {
        String message = "<html>\n"
                + "    <body>\n"
                + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border:0;max-width:600px;background:#f8f9fa; margin: auto; margin-top: 40px\"width=\"100%\">\n"
                + "            <tbody>\n"
                + "                <tr>\n"
                + "                    <td style=\"text-align:center;border-bottom-color:orange;border-bottom-style:solid;border-bottom-width:10px;border-top-width:0; padding-top: 15px; padding-bottom: 10px;\">\n"
                + "                          <img src=\"" + Constants.C3_HOST + "logo.png\" alt=\"V.E.T\">    \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px\">\n"
                + "                    <td style=\"padding-top: 30px;padding-left: 20px; font-weight: bold;\">\n"
                + "                        Xin chào " + name + ",  \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px\">\n"
                + "                    <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                      Dưới đây là mật khẩu mới cho tài khoản của bạn. Hãy đăng nhập và đổi lại mật khẩu:\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px; \">\n"
                + "                    <td style=\"padding-top: 50px; padding-right: 40px ;padding-left: 20px; text-align: center; padding-bottom: 20px\">\n"
                + "                       <button style=\"background-color: orange; border:none; line-height: 40px; color: white; font-weight: bold; padding: 0 10px; cursor: pointer;\">" + password + "</button>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px;\">\n"
                + "                    <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px; padding-bottom: 40px\">\n"
                + "                        Chúng tôi xin cảm ơn và chúc bạn có nhiều trải nghiệm tốt ở cửa hàng,\n"
                + "                        <br>\n"
                + "                        V.E.T\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "            </tbody>\n"
                + "        </table>\n"
                + "    </body>\n"
                + "</html>\n";
        return message;
    }

    public static String sendOrderToCustomer(String name, CartDTO c, String order_id,
            String name_receiver, String phone, String address, String type_payment
    ) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        String totalMoney = numberFormat.format(c.getCartTotalPrice());
        Map<String, OrderedBirdItem> birdList = c.getBirdList();
        Map<String, OrderedAccessoryItem> accessoryList = c.getAccessoryList();
        Map<String, OrderedBirdPairItem> birdPair = c.getBirdPairList();
        String message = "<html>\n"
                + "        <body>\n"
                + "\n"
                + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border:0;max-width:750px;background:#f8f9fa; margin: auto; margin-top: 40px\"width=\"100%\">\n"
                + "                <tbody>\n"
                + "                    <tr>\n"
                + "                        <td style=\"text-align:center;border-bottom-color:orange;border-bottom-style:solid;border-bottom-width:10px;border-top-width:0; padding-top: 15px; padding-bottom: 10px;\">\n"
                + "                          <img src=\"" + Constants.C3_HOST + "logo.png\" alt=\"V.E.T\">    \n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 30px;padding-left: 20px; font-weight: bold;font-size: 15px\">\n"
                + "                            Xin chào " + name + ",  \n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px;font-size: 15px\">\n"
                + "                            Cảm ơn Quý khách đã tin tưởng & sử dụng sản phẩm của V.E.T - Thế giới vẹt cảnh\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;font-size: 15px\">\n"
                + "                            Đơn mua hàng của Quý khách đã được phát hành. \n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                             <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\"> Mã đơn hàng: </p> \n"
                + "                            <p style=\"display: inline-block; font-size: 19px; color: red; margin-left: 5px;\">" + order_id + "</p> \n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                            <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\">Sản phẩm: </p>  \n"
                + "                          </td>\n"
                + "                    </tr>\n";
        for (OrderedBirdItem ob : birdList.values()) {
            Bird b = ob.getBird();
            int realPrice = (b.getPrice() - (b.getPrice() * b.getDiscount() / 100));
            String price = numberFormat.format(realPrice);
            if (b != null) {
                message += "                    <tr style=\"padding-top: 10px\">\n"
                        + "                        <td style=\"padding-top: 15px; padding-right:40px ;padding-left: 20px;\">\n"
                        + "                           - <p style=\"display: inline-block\"> 1 </p><p style=\"display: inline-block;margin-left: 3px;\"> " + b.getBird_name() + "</p> - <p style=\"display: inline-block\"> " + price + " ₫</p>"
                        + "                        </td>\n"
                        + "                    </tr>\n";
            }
        }
        for (OrderedAccessoryItem oa : accessoryList.values()) {
            Accessory a = oa.getAccessory();
            if (a != null) {
                int realPrice = (a.getUnit_price() - (a.getUnit_price() * a.getDiscount() / 100)) * oa.getOrder_quantity();
                String price = numberFormat.format(realPrice);
                if (oa.getFree_order() > 0) {
                    int total = oa.getFree_order() + oa.getOrder_quantity();
                    message += "                    <tr style=\"padding-top: 10px\">\n"
                            + "                        <td style=\"padding-top: 15px; padding-right:40px ;padding-left: 20px;\">\n"
                            + "                              - <p style=\"display: inline-block\"> " + total + " </p><p style=\"display: inline-block;margin-left: 3px;\"> " + a.getAccessory_name() + " (Tặng kèm " + oa.getFree_order() + ")" + "</p> - <p style=\"display: inline-block\"> " + price + " ₫</p>"
                            + "                        </td>\n"
                            + "                    </tr>\n";
                } else {
                    message += "                    <tr style=\"padding-top: 10px\">\n"
                            + "                        <td style=\"padding-top: 15px; padding-right:40px ;padding-left: 20px;\">\n"
                            + "                           - <p style=\"display: inline-block\"> " + oa.getOrder_quantity() + " </p><p style=\"display: inline-block;margin-left: 3px;\"> " + a.getAccessory_name() + "</p> - <p style=\"display: inline-block\"> " + price + " ₫</p>"
                            + "                        </td>\n"
                            + "                    </tr>\n";
                }
            }
        }
        for (OrderedBirdPairItem pb : birdPair.values()) {
            String price = numberFormat.format(2000000);
            if (pb.getBirdCustomer() != null) {
                message += "                    <tr style=\"padding-top: 10px\">\n"
                        + "                        <td style=\"padding-top: 15px; padding-right:40px ;padding-left: 20px;\">\n"
                        + "                           - <p style=\"display: inline-block\"> " + 1 + " </p><p style=\"display: inline-block;margin-left: 3px;\"> " + pb.getBirdCustomer().getName() + ", " + pb.getBirdShop().getBird_name() + " phí nhân giống" + "</p> - <p style=\"display: inline-block\"> " + price + " ₫</p>"
                        + "                        </td>\n"
                        + "                    </tr>\n";
            } else {
                message += "                    <tr style=\"padding-top: 10px\">\n"
                        + "                        <td style=\"padding-top: 15px; padding-right:40px ;padding-left: 20px;\">\n"
                        + "                           - <p style=\"display: inline-block\"> " + 1 + " </p><p style=\"display: inline-block;margin-left: 3px;\"> " + pb.getBirdMale().getBird_name() + ", " + pb.getBirdFemale().getBird_name() + " phí nhân giống" + "</p> - <p style=\"display: inline-block\"> " + price + " ₫</p>"
                        + "                        </td>\n"
                        + "                    </tr>\n";
            }
        }
        message += "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                            <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\"> Tổng giá trị đơn hàng: </p> \n"
                + "                            <p style=\"display: inline-block; font-size: 19px; color: red; margin-left: 5px;\">" + totalMoney + " ₫</p> \n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                            <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\">Trạng thái thanh toán: </p> \n"
                + "                            <p style=\"display: inline-block;font-size: 15px\">" + type_payment + "</p>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                            <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\">Tên người nhận: </p> \n"
                + "                            <p style=\"display: inline-block;font-size: 15px\">" + name_receiver + "</p>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                            <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\">Số liên lạc: </p> \n"
                + "                            <p style=\"display: inline-block;font-size: 15px\">" + phone + "</p>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                            <p style=\"display: inline-block; font-weight: bold;font-size: 15px;\"> Địa chỉ giao hàng: </p> \n"
                + "                            <p style=\"display: inline-block;font-size: 15px\">" + address + "</p>\n"
                + "                        </td>\n"
                + "                    </tr>\n";
        if (birdPair.size() <= 0) {
            message += "                    <tr style=\"padding-top: 10px\">\n"
                    + "                        <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px;font-size: 15px\">\n"
                    + "                            Quý khách vui lòng chờ giao hàng trong vòng 3-5 ngày kể từ ngày phát hành đơn mua hàng.\n"
                    + "                        </td>\n"
                    + "                    </tr>\n";
        } else {
            message += "                    <tr style=\"padding-top: 10px\">\n"
                    + "                        <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px;font-size: 15px\">\n"
                    + "                            Quý khách vui lòng theo dõi đơn hàng thường xuyên để cập nhật thông tin mới nhất về đơn hàng từ ngày phát hành đơn mua hàng.\n"
                    + "                        </td>\n"
                    + "                    </tr>\n";
        }
        message += "                    <tr style=\"padding-top: 10px\">\n"
                + "                        <td style=\"padding-top: 15px; padding-right: 40px ;padding-left: 20px;font-size: 15px\">\n"
                + "                            Nếu có vấn đề cần hỗ trợ Quý khách vui lòng liên hệ: Điện thoại: 19006955  email: thegioivetcanh@gmail.com\n"
                + "                        </td>\n"
                + "                    </tr>        \n"
                + "                    <tr style=\"padding-top: 10px;\">\n"
                + "                        <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px; padding-bottom: 40px;font-size: 15px\">\n"
                + "                            Chúc Quý khách sức khoẻ và thành công!,\n"
                + "                            <br>\n"
                + "                            V.E.T\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </tbody>\n"
                + "            </table>\n"
                + "        </body>\n"
                + "    </html>";
        return message;
    }
    
    public static String confirmBirdPair(String name, String orderID, int pairID){
        String message = "<html>\n"
                + "    <body>\n"
                + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border:0;max-width:600px;background:#f8f9fa; margin: auto; margin-top: 40px\"width=\"100%\">\n"
                + "            <tbody>\n"
                + "                <tr>\n"
                + "                    <td style=\"text-align:center;border-bottom-color:orange;border-bottom-style:solid;border-bottom-width:10px;border-top-width:0; padding-top: 15px; padding-bottom: 10px;\">\n"
                + "                          <img src=\"" + Constants.C3_HOST + "logo.png\" alt=\"V.E.T\">    \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px\">\n"
                + "                    <td style=\"padding-top: 30px;padding-left: 20px; font-weight: bold;\">\n"
                + "                        Xin chào " + name + ",  \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px\">\n"
                + "                    <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px;\">\n"
                + "                        Chào mừng bạn đã đến với V.E.T - Thế giới vẹt cảnh. Đơn hàng của bạn đã ấp nở, vui lòng xác nhận đơn hàng. Nếu bạn không xác nhận đơn hàng trong vòng 15 ngày thì shop sẽ tự động hủy đơn và không hoàn tiền.\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px; \">\n"
                + "                    <td style=\"padding-top: 50px; padding-right: 40px ;padding-left: 20px; text-align: center; padding-bottom: 20px\">\n"
                + "                        <a href=\"" + Constants.HOST + "MainController?action=NavToBirdPairDetail&order_id=" + orderID + "&pair_id=" + pairID + "\"><button style=\"background-color: orange; border:none; line-height: 40px; color: white; font-weight: bold; padding: 0 10px; cursor: pointer;\">Bấm nút</button></a>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr style=\"padding-top: 10px;\">\n"
                + "                    <td style=\"padding-top: 30px; padding-right: 40px ;padding-left: 20px; padding-bottom: 40px\">\n"
                + "                        Chúng tôi xin cảm ơn và chúc bạn có nhiều trải nghiệm tốt,\n"
                + "                        <br>\n"
                + "                        V.E.T\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "            </tbody>\n"
                + "        </table>\n"
                + "    </body>\n"
                + "</html>\n";
        return message;
    }
}
