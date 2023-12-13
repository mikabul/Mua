package kr.co.Mua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.Mua.bean.SongDto;

public class AlbumModifyValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SongDto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
}
