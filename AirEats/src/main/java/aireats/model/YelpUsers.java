package aireats.model;

import java.sql.Timestamp;
import java.util.Date;

public class YelpUsers {
	protected String userId;
	protected String userName;
	protected int reviewCount;
	protected Timestamp yelpingSince;
	protected int useful;
	protected int funny;
	protected int cool;
	protected int fans;
	protected Double averageStars;
	protected int complimentHot;
	protected int complimentMore;
	protected int complimentProfile;
	protected int complimentCute;
	protected int complimentList;
	protected int complimentNote;
	protected int complimentPlain;
	protected int complimentCool;
	protected int complimentFunny;
	protected int complimentWriter;
	protected int complimentPhotos;
	
	// Everything included
    public YelpUsers(String userId, String userName, 
    		int reviewCount, Timestamp yelpingSince, int useful, int funny, int cool, 
    		int fans, Double averageStars, int complimentHot, int complimentMore, int complimentProfile, 
    		int complimentCute, int complimentList, int complimentNote, int complimentPlain, int complimentCool, 
    		int complimentFunny, int complimentWriter, int complimentPhotos) {
        this.userId = userId;
        this.userName = userName;
        this.reviewCount = reviewCount;
        this.yelpingSince = yelpingSince;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.fans = fans;
        this.averageStars = averageStars;
        this.complimentHot = complimentHot;
        this.complimentMore = complimentMore;
        this.complimentProfile = complimentProfile;
        this.complimentCute = complimentCute;
        this.complimentList = complimentList;
        this.complimentNote = complimentNote;
        this.complimentPlain = complimentPlain;
        this.complimentCool = complimentCool;
        this.complimentFunny = complimentFunny;
        this.complimentWriter = complimentWriter;
        this.complimentPhotos = complimentPhotos;
    }

    // Only PK
    public YelpUsers(String userId) {
        this.userId = userId;
    }
    
    // user ID and username
    public YelpUsers(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    
    // Everything except PK
    public YelpUsers(String userName, 
    		int reviewCount, Timestamp yelpingSince, int useful, int funny, int cool, 
    		int fans, Double averageStars, int complimentHot, int complimentMore, int complimentProfile, 
    		int complimentCute, int complimentList, int complimentNote, int complimentPlain, int complimentCool, 
    		int complimentFunny, int complimentWriter, int complimentPhotos) {
        this.userName = userName;
        this.reviewCount = reviewCount;
        this.yelpingSince = yelpingSince;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.fans = fans;
        this.averageStars = averageStars;
        this.complimentHot = complimentHot;
        this.complimentMore = complimentMore;
        this.complimentProfile = complimentProfile;
        this.complimentCute = complimentCute;
        this.complimentList = complimentList;
        this.complimentNote = complimentNote;
        this.complimentPlain = complimentPlain;
        this.complimentCool = complimentCool;
        this.complimentFunny = complimentFunny;
        this.complimentWriter = complimentWriter;
        this.complimentPhotos = complimentPhotos;
    }
	
	// getters and setters
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getReviewCount() {
		return reviewCount;
	}
	
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	public Timestamp getYelpingSince() {
		return yelpingSince;
	}
	
	public void setYelpingSince(Timestamp yelpingSince) {
		this.yelpingSince = yelpingSince; 
	}
	
    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    public int getFunny() {
        return funny;
    }

    public void setFunny(int funny) {
        this.funny = funny;
    }

    public int getCool() {
        return cool;
    }

    public void setCool(int cool) {
        this.cool = cool;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public Double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

    public int getComplimentHot() {
        return complimentHot;
    }

    public void setComplimentHot(int complimentHot) {
        this.complimentHot = complimentHot;
    }
    
    public int getComplimentMore() {
    	return complimentMore;
    }
    
    public void setComplimentMore(int complimentMore) {
    	this.complimentMore = complimentMore;
    }

    public int getComplimentProfile() {
    	return complimentProfile;
    }
    
    public void setComplimentProfile(int complimentProfile) {
    	this.complimentProfile = complimentProfile;
    }
    
    public int getComplimentCute() {
    	return complimentCute;
    }
    
    public void setComplimentCute(int complimentCute) {
    	this.complimentCute = complimentCute;
    }
    
    public int getComplimentList() {
    	return complimentList;
    }
    
    public void setComplimentList(int complimentList) {
    	this.complimentList = complimentList;
    }
    
    public int getComplimentNote() {
    	return complimentNote;
    }
    
    public void setComplimentNote(int complimentNote) {
    	this.complimentNote = complimentNote;
    }
    
    public int getComplimentPlain() {
    	return complimentPlain;
    }
    
    public void setComplimentPlain(int complimentPlain) {
    	this.complimentPlain = complimentPlain;
    }
    
    public int getComplimentCool() {
    	return complimentCool;
    }
    
    public void setComplimentCool(int complimentCool) {
    	this.complimentCool = complimentCool;
    }
    
	public int getComplimentFunny() {
		return complimentFunny;
	}
    
	public void setComplimentFunny(int complimentFunny) {
		this.complimentFunny = complimentFunny;
	}
    
	public int getComplimentWriter() {
		return complimentWriter;
	}
	
	public void setComplimentWriter(int complimentWriter) {
		this.complimentWriter = complimentWriter;
	}
	
	public int getComplimentPhotos() {
		return complimentPhotos;
	}
	
	public void setComplimentPhotos(int complimentPhotos) {
		this.complimentPhotos = complimentPhotos; 
	}

	@Override
	public String toString() {
		return "YelpUsers [userId=" + userId + ", userName=" + userName + ", reviewCount=" + reviewCount
				+ ", yelpingSince=" + yelpingSince + ", useful=" + useful + ", funny=" + funny + ", cool=" + cool
				+ ", fans=" + fans + ", averageStars=" + averageStars + ", complimentHot=" + complimentHot
				+ ", complimentMore=" + complimentMore + ", complimentProfile=" + complimentProfile
				+ ", complimentCute=" + complimentCute + ", complimentList=" + complimentList + ", complimentNote="
				+ complimentNote + ", complimentPlain=" + complimentPlain + ", complimentCool=" + complimentCool
				+ ", complimentFunny=" + complimentFunny + ", complimentWriter=" + complimentWriter
				+ ", complimentPhotos=" + complimentPhotos + "]";
	}

}
