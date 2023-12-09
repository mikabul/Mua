package kr.co.Mua.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.dao.ChartDao;

@Service
public class ChartService {

	@Autowired
	private ChartDao chartDAO;

	@Autowired
	private InsertDBService insertDBService;

	// ------------�빐�떦�븯�뒗 �끂�옒�쓽 id瑜� 媛��졇�샂--------------
	public Integer chartSongMatch(String song_name, String artist_name, String album_name, int tempSong_id) {

		String temp_artist_name;
		Integer temp = chartDAO.chartSongMatch(song_name, artist_name, album_name);

		// null�씠�씪硫� 留덉�留� 怨듬갚�쓽 �뮮遺�遺� �샊�� ()瑜� �젣嫄� �썑 �븳踰덈뜑 �떆�룄
		if (temp == null) {

			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartSongMatch(song_name, temp_artist_name.trim(), album_name);
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 39: 媛��닔 �씠由� �옄瑜닿린 遺덇��뒫 =>" + artist_name);
			}

		}

		// null�씠�씪硫� �벑濡� �썑 諛섑솚
		if (temp == null) {
			insertDBService.insertDB(tempSong_id);
			temp = chartDAO.chartSongMatch(song_name, artist_name, album_name);
		}

		// �벑濡� �썑 null�씠�씪硫� 留덉�留� 怨듬갚�쓽 �뮮遺�遺� �샊�� ()瑜� �젣嫄� �썑 �븳踰덈뜑 �떆�룄
		if (temp == null) {

			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartSongMatch(song_name, temp_artist_name.trim(), album_name);
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 57: 媛��닔 �씠由� �옄瑜닿린 遺덇��뒫 =>" + artist_name);
			}

		}
		return temp;
	}

	// -----------�빐�떦�븯�뒗 �븘�떚�뒪�듃�쓽 id瑜� 媛��졇�샂--------------
	public Integer chartArtistmatch(int song_id, String artist_name, int tempSong_id) {

		String temp_artist_name;
		Integer temp = chartDAO.chartArtistmatch(song_id, artist_name);

		// temp媛� null�씪寃쎌슦 artist_name�뿉�꽌 留덉�留� 怨듬갚�쓽 �뮮遺�遺� �샊�� ()瑜� �젣嫄� �썑 �븳踰덈뜑 �떆�룄
		if (temp == null) {
			
			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartArtistmatch(song_id, temp_artist_name.trim());
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 77: 媛��닔 �씠由� �옄瑜닿린 遺덇��뒫 =>" + artist_name);
			}
			
		}
		
		// temp媛� null �씪 寃쎌슦 �벑濡� �썑 �옱�떆�룄
		if (temp == null) {
			insertDBService.insertDB(tempSong_id);
			temp = chartDAO.chartArtistmatch(song_id, artist_name);
		}
		
		// 洹몃옒�룄 temp媛� null�씪寃쎌슦 artist_name�뿉�꽌 留덉�留� 怨듬갚�쓽 �뮮遺�遺� �샊�� ()瑜� �젣嫄� �썑 �븳踰덈뜑 �떆�룄
		if (temp == null) {
			
			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartArtistmatch(song_id, temp_artist_name);
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 57: 媛��닔 �씠由� �옄瑜닿린 遺덇��뒫 =>" + artist_name);
			}
			
		}
		return temp;
	}

	// ---------------�빐�떦�븯�뒗 �븿踰붿쓽 id瑜� 媛��졇�샂-------------
	public Integer chartAlbumMatch(int song_id) {
		return chartDAO.chartAlbumMatch(song_id);
	}

	// ---------李⑦듃 �젙蹂대�� �뼸�뼱�샂------------
	public ArrayList<ChartDto> getChart() {

		ArrayList<ChartDto> chart = new ArrayList<ChartDto>();

		// URL
		String urlSearch = "https://www.melon.com/chart/index.htm?dayTime=";
		// �궇吏� �삎�떇 吏��젙(2023�뀈 11�썡 27�씪 13�떆=> 23112713)
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());

		Document searchResult;

		try {
			// �뀒�뒪�듃�슜, �궘�젣�븘�슂 10媛쒕줈 �젣�븳
			int limit = 0;
			// HTML�쟾臾� 媛��졇�삤湲�
			searchResult = Jsoup.connect(urlSearch + now).get();
			// 李⑦듃瑜� �씠猷⑤뒗 tbody�궡遺��쓽 tr�쓣 �쟾遺� 遺덈윭�샂
			Elements trElements = searchResult.select("tbody tr");
			int nothing = 1;

			// tr�쓣 �븯�굹�뵫 媛��졇�샂
			for (Element trElement : trElements) {
				// ==================�씠由�======================
				// ���옣�쓣 �쐞�븳 媛앹껜 �깮�꽦
				ChartDto temp = new ChartDto();

				// �끂�옒 �씠由�
				Elements nameElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01 a");
				temp.setSong_name(nameElements.text());

				// �엫�떆濡� �궗�슜�븷 song_id
				int tempSong_id = 0;
				try {
					String strSong_id = nameElements.attr("href");
					tempSong_id = Integer.parseInt(
							strSong_id.substring(strSong_id.lastIndexOf(",") + 1, strSong_id.lastIndexOf(")")));
				} catch (Exception e) {
					System.out.println("ChartService �엫�떆 Song_id �삤瑜�" + tempSong_id);
					e.printStackTrace();
				}
				// �븘�떚�뒪�듃 �씠由�
				Elements artistElements = trElement
						.select("td:nth-child(6) div.wrap_song_info div.ellipsis.rank02 span a");
				String tempArtist_name[] = new String[artistElements.size()];
				for (int i = 0; i < artistElements.size(); i++) {
					tempArtist_name[i] = artistElements.get(i).text();
				}
				temp.setArtist_name(tempArtist_name);
				// �븿踰� �씠由�
				Elements albumElements = trElement.select("td:nth-child(7)");
				temp.setAlbum_name(albumElements.text());

				// ================== �뜲�씠�꽣 踰좎씠�뒪 遺덈윭�삤湲� ==================
				// song_id
				temp.setSong_id(chartSongMatch(temp.getSong_name(), temp.getArtist_name()[0], temp.getAlbum_name(),
						tempSong_id));

				// artist_id, artist_thumbnail
				int tempArtist_id[] = new int[tempArtist_name.length];
				String tempArtist_thumbnail[] = new String[tempArtist_name.length];
				for (int i = 0; i < tempArtist_id.length; i++) {
					tempArtist_id[i] = chartArtistmatch(temp.getSong_id(), tempArtist_name[i], tempSong_id);
					tempArtist_thumbnail[i] = chartDAO.getArtist_thumbnail(tempArtist_id[i]);
				}
				temp.setArtist_id(tempArtist_id);
				temp.setArtist_thumbnail(tempArtist_thumbnail);

				// album_id
				temp.setAlbum_id(chartAlbumMatch(temp.getSong_id()));

				// �끂�옒�쓽 �뜽�꽕�씪�쓣 媛��졇�샂
				temp.setSong_thumbnail(chartDAO.getSong_thumbnail(temp.getSong_id()));

				// �끂�옒�쓽 醫뗭븘�슂 媛��닔瑜� 媛��졇�샂
				temp.setSong_thumbup(chartDAO.getCount_thumbup(temp.getSong_id()));
				chart.add(temp);
				
				// �뀒�뒪�듃�슜, �궘�젣�븘�슂 10媛쒕줈 �젣�븳
				if(++limit == 100) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

}
