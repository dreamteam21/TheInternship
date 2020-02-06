import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractData {
	public static void main(String[] args) throws IOException {
		StringBuilder html = new StringBuilder();;
        URL url = new URL("https://theinternship.io/");
        TreeMap<Integer, String> companyList = new TreeMap<Integer, String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                html.append(line);
            }
        }
        String htmlString = html.toString();
        Pattern pattern = Pattern.compile("(\\<div class=\"column is-one-third-desktop is-half-tablet\")(.*?)(\\</div></div></div>)");
        Matcher matcher = pattern.matcher(htmlString);
        ArrayList<String> subString = new ArrayList<String>();
        while(matcher.find()){
        	subString.add(htmlString.substring(matcher.start(), matcher.end()));        	
        }
        for(String s : subString) {
        	Pattern patternImg = Pattern.compile("(\\<img src=\")(.*?)(\")");
        	Pattern patternDesc = Pattern.compile("(\\<span class=\"list-company\" data-v-018ba4ef>)(.*)(<\\/span>)");
        	Matcher matcherImg = patternImg.matcher(s);
        	Matcher matcherDesc = patternDesc.matcher(s);
        	if(matcherImg.find()) {
        		if(matcherDesc.find()) {
        			//System.out.println(matcherImg.group(2)+" "+matcherDesc.group(2).length());
        			int i = 0;
        			while(companyList.containsKey(matcherDesc.group(2).length()+i)) i++;
        			companyList.put(matcherDesc.group(2).length()+i, matcherImg.group(2));
        		}
        	}
        }
        for(Integer i : companyList.keySet()) {
        	System.out.println(companyList.get(i));
        }
        jsonApiCompanies(companyList);
    }
	
	private static void jsonApiCompanies(TreeMap<Integer, String> cList) throws IOException {
		File file = new File("companies.json");
		BufferedWriter jWrite = new BufferedWriter(new FileWriter(file));
		jWrite.write("{ \"companies\" : [\n");
		for(Integer i : cList.keySet()) {
			if(i != cList.lastKey()) {
				jWrite.write("\t{ \"logo\": \"https://theinternship.io/"+cList.get(i)+"\"},\n");
			}
			else {
				jWrite.write("\t{ \"logo\": \"https://theinternship.io/"+cList.get(i)+"\"}\n] }");
			}
		}
		jWrite.close();
	}
}
