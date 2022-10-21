using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace lab2.Models
{
    public class Roster : Player
    {
        public static Roster fromBase(Player p) => new Roster
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
