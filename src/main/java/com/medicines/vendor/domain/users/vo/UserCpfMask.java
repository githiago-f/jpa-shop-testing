package com.medicines.vendor.domain.users.vo;

public class UserCpfMask {
	public static String applyMask(String cpf) {
		String strCpf = cpf.substring(3, 12);
		return "***" + strCpf + "**";
	}
}
