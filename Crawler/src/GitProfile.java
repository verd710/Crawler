
class GitProfile extends Profile {
	private String nickname;
	private String fullName;
	private String company;
	private String location;
	private String commonProgLang;

	private String mostPopularRepo;
	private Long mostPopularRepoStars;
	private String mostPopularRepoLang;

	public GitProfile(String nickname, String fullName, String company, String location, String commonProgLang,
			String mostPopularRep, Long mostPopularRepStars, String mostPopularRepLang) {
		this.nickname = nickname;
		this.fullName = fullName;
		this.company = company;
		this.location = location;
		this.commonProgLang = commonProgLang;
		this.mostPopularRepo = mostPopularRep;
		this.mostPopularRepoStars = mostPopularRepStars;
		this.mostPopularRepoLang = mostPopularRepLang;
	}

	public String getFullInfo() {
		return ("Nickname: " + nickname + "\nFull name: " + fullName + "\nCompany: " + company + "\nLocation: "
				+ location + "\nCommon programming language: " + commonProgLang + "\nMost popular repository: "
				+ mostPopularRepo + "\nStars: " + mostPopularRepoStars + "\nProgramming laguage: "
				+ mostPopularRepoLang);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCommonProgLang() {
		return commonProgLang;
	}

	public void setCommonProgLang(String commonProgLang) {
		this.commonProgLang = commonProgLang;
	}

	public String getMostPopularRep() {
		return mostPopularRepo;
	}

	public void setMostPopularRep(String mostPopularRep) {
		this.mostPopularRepo = mostPopularRep;
	}

	public Long getMostPopularRepStars() {
		return mostPopularRepoStars;
	}

	public void setMostPopularRepStars(Long mostPopularRepStars) {
		this.mostPopularRepoStars = mostPopularRepStars;
	}

	public String getMostPopularRepLang() {
		return mostPopularRepoLang;
	}

	public void setMostPopularRepLang(String mostPopularRepLang) {
		this.mostPopularRepoLang = mostPopularRepLang;
	}
}
