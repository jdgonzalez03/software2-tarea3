package Factory;

import ConvertBase64ToBinary.ConvertBase64ToBinary;
import ConvertBinaryToBase64.ConvertBinaryToBase64;
import ConvertBinaryToText.ConvertBinaryToText;
import ConvertTextToBinary.ConvertTextToBinary;
import ConvertTextToSHA.ConvertTextToSha256;
import ListWordsWithFrequency.ListWordsWithFrequency;
import LoadTextFile.LoadFileText;
import SearchWordsInText.SearchWordsInText;

import shared.FilterInterface;

public class Factory {
    public FilterInterface loadFile() { return new LoadFileText();}
    public FilterInterface convertTextToBinary() { return new ConvertTextToBinary();}
    public FilterInterface convertBinaryToText() { return new ConvertBinaryToText(); }
    public FilterInterface convertBinaryToBase64() {return new ConvertBinaryToBase64(); }
    public FilterInterface convertBase64ToBinary() { return new ConvertBase64ToBinary(); }
    public FilterInterface convertTextToSHA() { return new ConvertTextToSha256();  }
    public FilterInterface searchHelloWord() { return new SearchWordsInText(); }
    public FilterInterface listWordsWithFrequency() { return new ListWordsWithFrequency(); }
}
