package MyPackage;


	import java.awt.event.*;
	import java.io.IOException;
	import java.net.*;
	import javax.swing.*;
	import java.awt.*;

	public class uu extends JFrame implements ActionListener {
		/** * */
		private static final long serialVersionUID = 1L;
		private JTextArea text;// 信息接收文本域
		private JTextField ipText;// IP文本框
		private JTextField sendText;// 信息发送文本框
		private JButton button;// 发送按钮
		private DatagramSocket socket;// 数据报套接字
		private JScrollBar vsBar;// 滚动条

		public uu() {
			setTitle("UDP聊天程序");// 设置窗体标题
			setBounds(100, 100, 400, 300);// 窗体定位与大小
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭操作为退出程序
			setLayout(new BorderLayout());// 窗体使用边界布局管理器
			text = new JTextArea();// 实例化信息接收文本域
			text.setEditable(false);// 信息接收文本域不可编辑
			JScrollPane textPanel = new JScrollPane(text);// 信息接收文本域添加滚动面板
			vsBar = textPanel.getVerticalScrollBar();// 获取滚动面板的垂直滚动条
			add(textPanel, BorderLayout.CENTER);// 添加滚动面板到窗体居中位置
			JPanel panel = new JPanel();// 创建panel面板
			BorderLayout panelLayout = new BorderLayout();// 创建边界布局管理器
			panelLayout.setHgap(5);// 设置布局水平边界
			panel.setLayout(panelLayout);// 将布局管理器注入panel面板

			ipText = new JTextField("10.81.224.113");// 实例化IP文本框（请输入你自己IP地址）
			panel.add(ipText, BorderLayout.WEST);// 添加文本框到panel面板
			sendText = new JTextField();// 实例化信息发送文本框
			panel.add(sendText, BorderLayout.CENTER);// 添加信息发送文本框到panel
			button = new JButton("发送");// 实例化发送按钮
			panel.add(button, BorderLayout.EAST);// 添加按钮到panel面板
			add(panel, BorderLayout.SOUTH);// 添加面板到窗体
			setVisible(true);// 显示窗体 
			server();//调用Server（）方法
			button.addActionListener(this);// 添加按钮事件监听器
		}

		private void server() {
			try {
				socket = new DatagramSocket(9527);// 实例化数据报套接字
				byte[] buf = new byte[1024];
				final DatagramPacket dpl = new DatagramPacket(buf, buf.length);// 创建接收数据的数据包
				Runnable runnable = new Runnable() {// 定义线程
					public void run() {
						while (true) { // 使用无限循环体
							try {
								Thread.sleep(100);// 线程休眠时间为100ms
								socket.receive(dpl);// 接收数据包
								int length = dpl.getLength();
								String message = new String(dpl.getData(), 0, length);// 获取数据包的字符串信息

								String ip = dpl.getAddress().getHostAddress();
								if (!InetAddress.getLocalHost().getHostAddress().equals(ip))
									text.append(ip + ":\n   " + message + "\n");

								vsBar.setValue(vsBar.getMaximum());// 控制信息滚动
							} catch (IOException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				new Thread(runnable).start();
			} catch (

			SocketException e) {
				e.printStackTrace();
			}
		}

		public void actionPerformed(ActionEvent ev) {
			try {
				String ip = ipText.getText();// 获取IP文本框内容
				InetAddress address = InetAddress.getByName(ip);
				byte[] data = sendText.getText().getBytes();// 获取发送的数据
				DatagramPacket dp = new DatagramPacket(data, data.length, address, 9527);// 定义数据包
				String myip = InetAddress.getLocalHost().getHostAddress();// 获取本机IP 
				text.append(myip + ":\n   :" + sendText.getText() + "\n");//将发送信息添加到信息接收文本域中
				socket.send(dp);// 发送数据包
				sendText.setText(null);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void main(String[] args) {
			uu udp = new uu();
			udp.setVisible(true);
		}
	}

