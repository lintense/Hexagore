

import gnu.prolog.database.PrologTextLoaderError;
import gnu.prolog.demo.mentalarithmetic.NoAnswerException;
import gnu.prolog.io.TermWriter;
import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.Term;
import gnu.prolog.term.VariableTerm;
import gnu.prolog.vm.Environment;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.Interpreter.Goal;
import gnu.prolog.vm.PrologCode;
import gnu.prolog.vm.PrologException;

import java.io.IOException;

import java.util.List;


/**
 * This is the main class of the mentalarithemtic program. It is runnable as a
 * stand alone program.
 * 
 * Should be thread safe.
 * 
 * @author Daniel Thomas
 * 
 * http://www.gprolog.org/manual/html_node/index.html
 */
public class TestGnuPro {

	/**
	 * Whether we have done {@link #setup()} or not.
	 */
	private static boolean issetup = false;

	/**
	 * The {@link Environment} we are using.
	 * 
	 * @see #setup() for creation
	 * @see #generateQuestion(int,int) for usage
	 */
	private static PrologWrapper env;
	/**
	 * The {@link Interpreter} we are using.
	 * 
	 * @see #setup() for creation
	 * @see #generateQuestion(int,int) for usage
	 */
	private static Interpreter interpreter;

	/**
	 * Standalone entry point for the program
	 * 
	 * @param args
	 *          as detailed in {@link #USAGE}
	 * @throws IOException 
	 * @see #USAGE
	 */
	public static void main(String[] args)
	{
		int limit, length;// @see #USAGE
		
		try
		{
			// Get the question to ask
//			Pair<String, Integer> question = 
			generateQuestion();
			// Print out the question
//			System.out.print(question.left + ": ");

			// Get a reader to read in the answer
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// Read in the answer
//			String answer = reader.readLine();
//			try
//			{// Try to parse the number as an integer
//				int value = Integer.parseInt(answer);
//				if (question.right == value)
//				{
//					System.out.println("Correct.");
//				}
//				else
//				{
//					System.out.println("Wrong. Answer was: " + question.right);
//				}
//			}// Tell the user what went wrong if it doesn't work
//			catch (NumberFormatException e)
//			{
//				System.err.println(String.format("Not a number: (%s)", answer));
//			}
		}// Something went wrong: tell the user.
		catch (PrologException e)
		{
			System.err.println(e.toString());
		}
		catch (NoAnswerException e)
		{
			System.err.println(e.toString());
		}
//		catch (IOException e)
//		{
//			System.err.println(e.toString());
//		}
	}

	public static void generateQuestion() throws PrologException, NoAnswerException
	{
		setup();

		// // Construct the question.
		// Create variable terms so that we can pull the answers out later
		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");
//		VariableTerm z = new VariableTerm("Z");

		// Create the arguments to the compound term which is the question
		//Term[] args = { new IntegerTerm(limit), new IntegerTerm(length), listTerm, answerTerm };
		;
		
		// Construct the question
		CompoundTerm goalTerm1 = new CompoundTerm(AtomTerm.get("set_phase"), new Term[] {AtomTerm.get("move")});
		debug();
		int r = interpreter.execute(interpreter.prepareGoal(goalTerm1)); //,retract(current_phase(_))")));
		debug();
		CompoundTerm goalTerm2 = new CompoundTerm(AtomTerm.get("current_phase"), new Term[]{y});
		interpreter.execute(interpreter.prepareGoal(goalTerm2));
		Term xv2 = y.dereference();
		System.out.println("--->  " + TermWriter.toString(xv2));
		
		
		
		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get("goal"), new Term[]{x});
		synchronized (interpreter)// so that this class is thread safe.
		{
			// Print out any errors
			debug();

			// Execute the goal and return the return code.
			
			
			
			Goal g = interpreter.prepareGoal(goalTerm);

			// If it succeeded.
			int rc = PrologCode.SUCCESS;
			while (rc == PrologCode.SUCCESS) // || rc == PrologCode.SUCCESS_LAST)
			{
				rc = interpreter.execute(g);
				// Create the answer
//				Pair<String, Integer> answer = new Pair<String, Integer>(null, 0);
//				StringWriter w = new StringWriter();
//				TermWriter tw = new TermWriter(w);
				
				
				// Get hold of the actual Terms which the variable terms point to
				Term xv = x.dereference();
//				Term yv = y.dereference();
//				Term zv = z.dereference();
				
//				System.out.println(listToString((CompoundTerm)xv));
				System.out.println("--->  " + TermWriter.toString(xv));
//				System.out.println(TermWriter.toString(yv));
//				System.out.println(TermWriter.toString(zv));

				
//				// Check it is valid
//				if (list != null)
//				{
//					if (list instanceof CompoundTerm)
//					{
//						CompoundTerm cList = (CompoundTerm) list;
//						if (cList.tag == TermConstants.listTag)// it is a list
//						{// Turn it into a string to use.
//							answer.left = TermWriter.toString(list);
//						}
//						else
//						{
//							throw new NoAnswerException("List is not a list but is a CompoundTerm: " + list);
//						}
//					}
//					else
//					{
//						throw new NoAnswerException("List is not a list: " + list);
//					}
//				}
//				else
//				{
//					throw new NoAnswerException("List null when it should not be null");
//				}
//				if (value != null)
//				{
//					if (value instanceof IntegerTerm)
//					{
//						answer.right = ((IntegerTerm) value).value;
//					}
//					else
//					{
//						throw new NoAnswerException("Answer is not an integer: (" + value + ") but List is:" + list);
//					}
//				}
//				else
//				{
//					throw new NoAnswerException("Answer null when it should not be null");
//				}
//
//				return answer;
//			}
//			else
//			{
//				throw new NoAnswerException("Goal failed");
			}
		}
	}
//	private static String listToString(CompoundTerm list) {
//		ArrayList<Term> al = new ArrayList();
//		CompoundTerm.toCollection(list, al);
//		Collections.reverse(al);
//		StringBuilder b = new StringBuilder();
//		al.forEach(c -> b.append((char)((IntegerTerm)c).value));
//		return b.toString();
//	}
	/**
	 * Ensure that we have an environment and have loaded the prolog code and have
	 * an interpreter to use.
	 * @throws IOException 
	 */
	private synchronized static void setup()
	{
		if (issetup)
		{
			return;// don't setup more than once
		}

//		final PipedInputStream pipedInputStream = new PipedInputStream();
//		final PipedOutputStream pipedOutputStream = new PipedOutputStream();
//		pipedInputStream.connect(pipedOutputStream);
		
		// Construct the environment
//		env = new Environment(pipedInputStream, System.out);
		env = new PrologWrapper();
		debug();
//		env.getPrologTextLoaderState().

		// get the filename relative to the class file
		env.ensureLoaded(AtomTerm.get(TestGnuPro.class.getResource("test0.pro").getFile()));
		env.ensureLoaded(AtomTerm.get(TestGnuPro.class.getResource("test1.pro").getFile()));
		env.ensureLoaded(AtomTerm.get(TestGnuPro.class.getResource("test2.pro").getFile()));
		
//		String goal = "goal(Q):- rule(X,Y,Z),check_do(Y),check_do(Z),Q=choose_from_list([hex(HEX),monster(M)]), member(Q,Z).";
//		InputStream in = new ByteArrayInputStream(goal.getBytes(Charset.forName("UTF-8")));
		
		
		
		// Get the interpreter.
		interpreter = env.createInterpreter();
		// Run the initialization
		env.runInitialization(interpreter);
		
		
//		byte[] buffer = new byte[1024];
//		int len = in.read(buffer);
//		while (len != -1) {
//			pipedOutputStream.write(buffer, 0, len);
//		    len = in.read(buffer);
//		}
//		env.ensureLoaded(IntegerTerm.get(1));
		env.ensureLoaded("X1", ":- multifile(goal/1). :- dynamic(goal/1). goal(Q).");
		env.runInitialization(interpreter);
		env.ensureLoaded("X2", ":- retractall(goal(_)). goal(Q):-Q=2.");
		env.runInitialization(interpreter);
		env.ensureLoaded("X3", "goal(Q):- rule(X,Y,Z),check_do(Y),check_do(Z),Q=choose_from_list([hex(HEX),monster(M)]), member(Q,Z).");
		env.runInitialization(interpreter);
		
		// So that we don't repeat ourselves
		issetup = true;
	}

	/**
	 * Collect debugging information if something has gone wrong in particular get
	 * any {@link PrologTextLoaderError PrologTextLoaderErrors} which were created
	 * during loading.
	 */
	private static void debug()
	{
		List<PrologTextLoaderError> errors = env.getLoadingErrors();
		for (PrologTextLoaderError error : errors)
		{
			error.printStackTrace();
		}

		/*
		 * Map<AtomTerm, Term> atom2flag = env.getPrologFlags(); Set<AtomTerm> atoms
		 * = atom2flag.keySet(); for (AtomTerm a : atoms) {
		 * System.out.println(a.toString() + " => " + atom2flag.get(a)); }
		 */
	}
//	private static InputStream getInputStream(String intString) {
//		String goal = "goal(Q):- rule(X,Y,Z),check_do(Y),check_do(Z),Q=choose_from_list([hex(HEX),monster(M)]), member(Q,Z).";
//		InputStream in = new ByteArrayInputStream(goal.getBytes(Charset.forName("UTF-8")));
//		return in;
//	}
//	private static class MyEnvironment extends Environment {
//		protected void createTextLoader()
//		{
//			prologTextLoaderState = new MyPrologTextLoaderState(this);
//		}
//	}
//	private static class MyPrologTextLoaderState extends PrologTextLoaderState {
//		public MyPrologTextLoaderState(Environment env) {
//			super(env);
//			// TODO Auto-generated constructor stub
//		}
//		public void ensureLoaded(Term term)
//		{
//			if (!loadedFiles.contains(getInputName(term)))
//			{
//				loadedFiles.add(getInputName(term));
//				new PrologTextLoader(this, term);
//			}
//		}
//		protected InputStream getInputStream(Term term) throws IOException
//		{
//			if (term.getTermType() == Term.INTEGER) {
//				return TestGnuPro.getInputStream(term.toString());
//			}
//			return super.getInputStream(term);
//		}
//	}
}
