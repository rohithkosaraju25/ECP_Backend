package com.cg.sprint1.proj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.cg.sprint1.proj.entities.*;
import com.cg.sprint1.proj.exceptions.*;

public interface IAdminService {

	public boolean addEngineer(int engineerId, String password, String engineerName, String domain);

	public boolean changeDomain(int engineerId, String newDomain)
			throws InValidDomainException, InvalidEngineerIdException;

	public boolean removeEngineer(int engineerId) throws InvalidEngineerIdException;

	public List<Complaint> getComplaintsByProducts(String productCategoryName);
	
	public Complaint getComplaintByComplaintId(int complaintId);

	public List<Complaint> getComplaints(String status, String productCategoryName);

	public boolean replaceEngineerFromComplaint(int complaintId)
			throws InValidDomainException, InValidComplaintIdException;

	public Optional<Engineer> getEngineerbyId(int engineerId);

	public int genEngineerId(List<Engineer> engineerList, int engineerId);
	
	public List<Complaint> getComplaintsByRequestStatus();
	
	public void setComplaintRequestStatus(int complaintId);
	

}
