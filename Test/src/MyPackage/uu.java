package MyPackage;


	import java.awt.event.*;
	import java.io.IOException;
	import java.net.*;
	import javax.swing.*;
	import java.awt.*;

	public class uu extends JFrame implements ActionListener {
		/** * */
		private static final long serialVersionUID = 1L;
		private JTextArea text;// ��Ϣ�����ı���
		private JTextField ipText;// IP�ı���
		private JTextField sendText;// ��Ϣ�����ı���
		private JButton button;// ���Ͱ�ť
		private DatagramSocket socket;// ���ݱ��׽���
		private JScrollBar vsBar;// ������

		public uu() {
			setTitle("UDP�������");// ���ô������
			setBounds(100, 100, 400, 300);// ���嶨λ���С
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Ĭ�Ϲرղ���Ϊ�˳�����
			setLayout(new BorderLayout());// ����ʹ�ñ߽粼�ֹ�����
			text = new JTextArea();// ʵ������Ϣ�����ı���
			text.setEditable(false);// ��Ϣ�����ı��򲻿ɱ༭
			JScrollPane textPanel = new JScrollPane(text);// ��Ϣ�����ı�����ӹ������
			vsBar = textPanel.getVerticalScrollBar();// ��ȡ�������Ĵ�ֱ������
			add(textPanel, BorderLayout.CENTER);// ��ӹ�����嵽�������λ��
			JPanel panel = new JPanel();// ����panel���
			BorderLayout panelLayout = new BorderLayout();// �����߽粼�ֹ�����
			panelLayout.setHgap(5);// ���ò���ˮƽ�߽�
			panel.setLayout(panelLayout);// �����ֹ�����ע��panel���

			ipText = new JTextField("10.81.224.113");// ʵ����IP�ı������������Լ�IP��ַ��
			panel.add(ipText, BorderLayout.WEST);// ����ı���panel���
			sendText = new JTextField();// ʵ������Ϣ�����ı���
			panel.add(sendText, BorderLayout.CENTER);// �����Ϣ�����ı���panel
			button = new JButton("����");// ʵ�������Ͱ�ť
			panel.add(button, BorderLayout.EAST);// ��Ӱ�ť��panel���
			add(panel, BorderLayout.SOUTH);// �����嵽����
			setVisible(true);// ��ʾ���� 
			server();//����Server��������
			button.addActionListener(this);// ��Ӱ�ť�¼�������
		}

		private void server() {
			try {
				socket = new DatagramSocket(9527);// ʵ�������ݱ��׽���
				byte[] buf = new byte[1024];
				final DatagramPacket dpl = new DatagramPacket(buf, buf.length);// �����������ݵ����ݰ�
				Runnable runnable = new Runnable() {// �����߳�
					public void run() {
						while (true) { // ʹ������ѭ����
							try {
								Thread.sleep(100);// �߳�����ʱ��Ϊ100ms
								socket.receive(dpl);// �������ݰ�
								int length = dpl.getLength();
								String message = new String(dpl.getData(), 0, length);// ��ȡ���ݰ����ַ�����Ϣ

								String ip = dpl.getAddress().getHostAddress();
								if (!InetAddress.getLocalHost().getHostAddress().equals(ip))
									text.append(ip + ":\n   " + message + "\n");

								vsBar.setValue(vsBar.getMaximum());// ������Ϣ����
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
				String ip = ipText.getText();// ��ȡIP�ı�������
				InetAddress address = InetAddress.getByName(ip);
				byte[] data = sendText.getText().getBytes();// ��ȡ���͵�����
				DatagramPacket dp = new DatagramPacket(data, data.length, address, 9527);// �������ݰ�
				String myip = InetAddress.getLocalHost().getHostAddress();// ��ȡ����IP 
				text.append(myip + ":\n   :" + sendText.getText() + "\n");//��������Ϣ��ӵ���Ϣ�����ı�����
				socket.send(dp);// �������ݰ�
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

