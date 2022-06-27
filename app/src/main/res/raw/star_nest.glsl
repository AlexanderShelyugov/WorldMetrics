// Star Nest by Pablo Roman Andrioli


#define iterations 17
#define formuparam 0.53

#define volsteps 5
#define stepsize 0.1

#define zoom   0.800
#define tile   0.850
#define speed  0.010

#define brightness 0.0015
#define darkmatter 0.300
#define distfading 0.730
#define saturation 0.850


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    //get coords and direction
    vec2 uv=fragCoord.xy/iResolution.xy-.5;
    uv.y*=iResolution.y/iResolution.x;
    vec3 dir=vec3(uv*zoom,1.)*.5;
    float time=iTime*speed+.25;
    vec3 from=vec3(.1,.5,0.7);
    from+=vec3(time*2.,time,-2.);

    //volumetric rendering
    float s=0.1,fade=1.,s2,s4;
    vec3 v=vec3(0.);
    for (int r=0; r<volsteps; r++) {
        vec3 p=from+s*dir;
        p = abs(vec3(tile)-mod(p,vec3(tile*2.))); // tiling fold
        float pa,a=pa=0.,lp;
        for (int i=0; i<iterations; i++) {
            p=abs(p)/dot(p,p)-formuparam; // the magic formula
            lp = length(p);
            a+=abs(lp-pa); // absolute sum of average change
            pa=lp;
        }
        float dm=max(0.,darkmatter-a*a*.001); //dark matter
        a*=a*a; // add contrast
        if (r>6) fade*=1.-dm; // dark matter, don't render near
        v+=fade;
        s2 = s * s;
        s4 = s2 * s2;
        v+=vec3(s4,s2,s)*a*brightness*fade; // coloring based on distance
        fade*=distfading; // distance fading
        s+=stepsize;
    }
    v=mix(vec3(length(v)),v,saturation); //color adjust
    fragColor = vec4(v*.01,1.);
}