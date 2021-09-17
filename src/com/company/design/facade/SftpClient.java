package com.company.design.facade;

/* facade  : ftp, reader, writer 의존성 모두 갖고 있다.
    객체를 한 번 감싸면서 모든 의존성을 SftpClient가 가져가고, 새로운 인터페이스를 제공한다.(connect/disconnect/read/write)
 */
public class SftpClient {
    private FTP ftp;
    private Writer writer;
    private Reader reader;
    
    public SftpClient(FTP ftp,Reader reader, Writer writer){
        this.ftp = ftp;
        this.reader = reader;
        this.writer = writer;
    }
    //host,port,path,fileName만 받아서 해당 객체를 사용할 수 있도록 오버로딩
    public SftpClient(String host, int port, String path, String fileName){
        this.ftp = new FTP(host, port, path);
        this.reader = new Reader(fileName);
        this.writer = new Writer(fileName);
    }

    public void connect(){
        ftp.connect();
        ftp.moveDirectory();
        writer.fileConnect();
        reader.fileConnect();
    }

    public void disconnect(){
        reader.fileDisconnect();
        writer.fileDisconnect();
        ftp.disconnect();
    }

    public void read(){
        reader.fileRead();
    }

    public void write(){
        writer.write();
    }


}
