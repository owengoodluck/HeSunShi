package com.xseagull.boxing.util;

import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IkanalyzerUtils {

	public static StringBuffer sendGet(String text) throws Exception {

		// 独立Lucene实现
		StringReader re = new StringReader(text);
		IKSegmenter ik = new IKSegmenter(re, true);
		Lexeme lex = null;
		StringBuffer str = new StringBuffer();
		try {
			while ((lex = ik.next()) != null) {
				String s = lex.getLexemeText() + ",";
				str.append(s);
			}
			
		} catch (Exception e) {
		}

		return str;
	}

}
