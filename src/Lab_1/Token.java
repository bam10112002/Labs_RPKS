package Lab_1;

class Token
{
    enum Type {OPEN, CLOSE}
    public Type type;
    public Character ch;
    public Token conjugate;
    Token(Type _type, Character _ch, Token _conjugate)
    {
        type = _type;
        ch = _ch;
        conjugate = _conjugate;
    }
}
