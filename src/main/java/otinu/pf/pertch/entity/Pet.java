package otinu.pf.pertch.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class Pet { //★
	
	/* ★
	 * Java16以上なら、「class」ではなく、「record」が使えるなら便利なため活用していくべき。
	 * ただし、今回は下記の理由からrecordは使わない。
	 * ①現状はrecordとJPAなどを組み合わせる方法が双方で用意されていない。
	 * ②recordはgetter()やtoString()などを自動生成してくれるが、setter()は用意されない
	 * 　⇒Spring使うなら現状はlombok活用の方が情報量多い
	 * 
	 */

	/* ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // これを付けるカラムはSerialにする必要がある
	@Column(name = "id")
	private Integer id;
	// 個人用のため今回はInteger指定のまま。一般向けなら型はLongにするべき
	
	/* 名前 */
	@Column(name = "name")
	private String name;
	
	/* 年齢 */
	@Column(name = "age")
	private Integer age;
	
	/* 性別 */
	@Column(name = "sex")
	private Boolean sex;
	
	/* 特徴 */
	@Column(name = "charm_point")
	private String charmPoint; // 目視でないと確認しにくい情報などを共有できるようにする
	
	/* 住処の郵便番号 */
	@Column(name = "post_cord") // おばあちゃんの犬を別居の娘が代わりに探している場合など、Userに郵便番号もたせると不便になりそう
	private String postCord;
	
	/* 住処の住所 */
	@Column(name = "address")
	private String address;
	
	/* 飼い主 */
	@Column(name = "image")
	private String image;
	
	/* 登録日時 */
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	/* 更新日時 */
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	/*
	 *  Owner has many Pet にするための外部キー
	 *  ⇒目撃情報を投稿するにはログインが必要で、投稿する際はログイン情報と結びつける
	 *  　⇒投稿への責任追及性を付与する
	 */
	@ManyToOne(optional = true) // optional有効で、n:1の1側がNULLでもDB登録可能になる(デフォルトはtrue)
	@JoinColumn(name = "owner_id")
	private Owner owner;
}
