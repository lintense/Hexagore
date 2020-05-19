import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

import gnu.prolog.database.PrologTextLoaderState;
import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.Term;
import gnu.prolog.vm.Environment;


public class PrologWrapper extends Environment {
//	private int count = 0;
	private HashMap<String,String> requests = new HashMap<String,String>();
	
	public void ensureLoaded(String name, String code) {
		requests.put("'" + name + "'", code);
		ensureLoaded(AtomTerm.get(name));
	}
	private InputStream getInput(String name) {
		String input = requests.remove(name);
//		String goal = "goal(Q):- rule(X,Y,Z),check_do(Y),check_do(Z),Q=choose_from_list([hex(HEX),monster(M)]), member(Q,Z).";
		InputStream in = new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8")));
		return in;
	}
	protected void createTextLoader() {
		prologTextLoaderState = new PrologTextLoaderStateWrapper(this);
	}

	private class PrologTextLoaderStateWrapper extends PrologTextLoaderState {
		public PrologTextLoaderStateWrapper(Environment env) {
			super(env);
		}
//		public void ensureLoaded(Term term)
//		{
//			if (!loadedFiles.contains(getInputName(term))) {
//				loadedFiles.add(getInputName(term));
//				new PrologTextLoader(this, term);
//			}
//		}
		protected InputStream getInputStream(Term term) throws IOException
		{
//			ClassLoader.class.getResourceAsStream("/path/file.ext");
			return term.getTermType() == Term.ATOM && requests.containsKey(term.toString())
				? getInput(term.toString())
				: super.getInputStream(term);
		}
//		protected InputStream getInputStream(Term term) throws IOException
//		{
//			if (requests.containsKey(term.toString()))
//				return getInput(term.toString());
//			
//			if (term instanceof AtomTerm) // argument is an atom, which is an filename
//			{
//				return new FileInputStream(resolveInputFile(((AtomTerm) term).value));
//			}
//			else if (term instanceof CompoundTerm)
//			{
//				CompoundTerm ct = (CompoundTerm) term;
//				if (ct.tag == fileTag)
//				{
//					if (!(ct.args[0] instanceof AtomTerm))
//					{
//						throw new IOException("unknown type of datasource");
//					}
//					return getInputStream(ct.args[0]);
//				}
//				else if (ct.tag == urlTag || ct.tag == resourceTag)
//				{
//					URL url;
//					if (!(ct.args[0] instanceof AtomTerm))
//					{
//						throw new IOException("unknown type of datasource");
//					}
//					AtomTerm arg = (AtomTerm) ct.args[0];
//					if (ct.tag == urlTag)
//					{
//						url = new URL(arg.value);
//					}
//					else
//					// resource tag
//					{
//						url = PrologTextLoaderState.class.getResource(arg.value);
//						if (url == null)
//						{
//							throw new IOException("resource not found");
//						}
//					}
//					return url.openStream();
//				}
//			}
//			throw new IOException("unknown type of datasource");
//		}
	}
}
