package otinu.pf.pertch;

public class Constant {
	
	// 共通
	public final static String UPLOAD_FILE_ENCODING_ERROR = "データ送信時に予期せぬエラーが発生しました";

	// OwnerController
	public final static String LOGIN_ERROR = "ログインに失敗しました";
	public final static String MAIL_ADDRESS_OVERLAPPING_ERROR = "入力のメールアドレスは登録済みでした";
	public final static String MYPAGE_MOVE_ERROR = "マイページに移動できませんでした";

	public final static String FINISH_USER_REGISTRATION = "登録が完了しました";
	public final static String FINISH_MYPAGE_UPDATE = "マイページの更新が完了しました";

	// PetController
	public final static Integer UPLOAD_FILE_MAX_SIZE = 4200000;
	public final static String UPLOAD_FILE_SIZE_ERROR = "ファイルアップロードに失敗しました。ファイルは4MBまでです";
	public final static String FINISH_PET_REGISTRATION = "ペット情報の登録が完了しました";
	public final static String NO_PET_ERROR = "該当のペット情報が見つかりませんでした";
	public final static String FINISH_PET_UPDATE = "ペット情報の更新が完了しました";
	public final static String FINISH_PET_DELETE = "削除が完了しました";
	
	// PetCommentServiceImpl
	public final static String LITERAL_T = "T";
	public final static String LITERAL_HALF_SPACE = " ";
	public final static String LITERAL_BAR = "-";
	public final static String LITERAL_SLASH = "/";
	public final static String DATEFORMAT = "yyyy/MM/dd HH:mm";
	
	// PetServiceImpl
	public final static String DATA_FORMAT = "ASCII";
	public final static String IMAGE_PNG = "image/png";
	public final static String IMAGE_JPEG = "image/jpeg";
	public final static String IMAGE_GIF = "image/gif";
	public final static String BASE64_PNG = "data:image/png;base64,";
	public final static String BASE64_JPEG = "data:image/jpeg;base64,";
	public final static String BASE64_GIF = "data:image/gif;base64,";

}
