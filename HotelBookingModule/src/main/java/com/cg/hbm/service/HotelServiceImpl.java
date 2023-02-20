package com.cg.hbm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.hbm.entity.Hotel;
import com.cg.hbm.exception.RecordNotFoundException;
import com.cg.hbm.repository.IHotelRepository;

@Service
public class HotelServiceImpl implements IHotelService {
	@Autowired
	private IHotelRepository hotelRepository;

	@Override
	public Hotel addHotel(Hotel hotel) {

		return hotelRepository.save(hotel);
	}
	
	

	@Override
	public Hotel getHotelById(int id) throws RecordNotFoundException {
		if(hotelRepository.existsById(id)) {

		return hotelRepository.getReferenceById(id);
		}
		else {
			throw new RecordNotFoundException("No hotel exists with this id = "+id);

		}
		//return null;
	}

	@Override
	public List<Hotel> getAllHotels() {

		return hotelRepository.findAll();
	}

	@Override
	public Hotel deleteHotelById(int hId) {
		Hotel savedHotel = hotelRepository.findById(hId).get();
		hotelRepository.deleteById(hId);
		return savedHotel;
	}
	
	/*@Override
	public Hotel updateHotel(int id,Hotel hotel)  {
		List<Hotel> hotels=new ArrayList<>();
		for(int i=0;i<hotels.size();i++) {
			Hotel h=hotels.get(i);
			
			if(h.equals(id)) {
				hotels.set(i,hotel);
			}
		}
		return hotel;
		//return hotelRepository.save(hotel);
		
	}*/
	
	
	@Override
	@Transactional
	public Hotel updateHotel(Hotel hotel) throws RecordNotFoundException {
		Optional<Hotel> updateHotel = hotelRepository.findById(hotel.getHotelId());
		if (updateHotel.isPresent()) {
			return hotelRepository.save(hotel);}
		else {
			throw new RecordNotFoundException("Invalid hotel details");
		}
		}





}
