

namespace GrpcService1.Models
{
    public class Temp: Player
    {
        public static Temp fromBase(Player p) => new Temp
        {
            playerid = p.playerid,
            fname = p.fname,
            sname = p.sname,
            jersey = p.jersey,
            position = p.position,
            height = p.height,
            weight = p.weight,
            birthday = p.birthday,
            birthstate = p.birthstate,
            birthcity = p.birthcity,
        };
    }
}
